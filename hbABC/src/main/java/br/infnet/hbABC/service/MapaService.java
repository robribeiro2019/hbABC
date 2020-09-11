package br.infnet.hbABC.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.infnet.hbABC.model.DiaOperacao;
import br.infnet.hbABC.model.Mapa;
import br.infnet.hbABC.reader.ReaderCSV;

@Service
public class MapaService {
	
	@Autowired
	private ReaderCSV reader;	
	
	private Mapa mapa;
	private Mapa ema9;
	private Mapa ema12;
	private Mapa ema26;
	private Mapa macdLinha;
	private Mapa macdHistograma;
	private List<Mapa> mapas;
	
	@PostConstruct
	private void leArquivo() {

		mapas = new ArrayList<>();

		mapa = reader.lerArquivoCSV();

		mapas.add(mapa);

		ema9 = calculaEma(9, mapa.getDias());
		mapas.add(ema9);

		ema12 = calculaEma(12, mapa.getDias());
		mapas.add(ema12);

		ema26 = calculaEma(26, mapa.getDias());
		mapas.add(ema26);
		
		macdLinha = calculaMacdLinha();
		mapas.add(macdLinha);
		
		macdHistograma = calculaMacdHistograma();
		mapas.add(macdHistograma);
		
	}	
	
	private Mapa calculaMacdHistograma() {
		
		Mapa macdHistograma = new Mapa();
		List<DiaOperacao> dias = new ArrayList<>();
		DiaOperacao dia;
		BigDecimal close;
		
		Mapa signalLine = calculaEma(9, macdLinha.getDias());
		
		for ( int i = 0; i < signalLine.getDias().size(); i++){
			close = macdLinha.getDias().get(i).getClose().subtract(signalLine.getDias().get(i).getClose());
			dia = new DiaOperacao(signalLine.getDias().get(i).getDate(), close);
			dias.add(dia);
		}
		
		macdHistograma.setDias(dias);
		return macdHistograma;
	}	
	
	private Mapa calculaMacdLinha() {
		
		Mapa macdLinha = new Mapa();
		List<DiaOperacao> dias = new ArrayList<>();
		DiaOperacao dia;
		BigDecimal close;
		
		for(int i = 0; i < mapa.getDias().size() ; i++) {
			close = ema12.getDias().get(i).getClose().subtract(ema26.getDias().get(i).getClose());
			dia = new DiaOperacao(mapa.getDias().get(i).getDate(), close);
			dias.add(dia);
		}
		
		macdLinha.setDias(dias);
		return macdLinha;
	}	
	
	public List<Mapa> getMapas() {
		return this.mapas;
	}	

	private Mapa calculaEma(int periodo, List<DiaOperacao> diasParaOCalculo) {

		List<DiaOperacao> dias = new ArrayList<>();

		DiaOperacao diaMapaComum;
		double multiplicador = getMultiplicador(periodo);
		
		for (int i = 0; i < diasParaOCalculo.size(); i++) {

			diaMapaComum = diasParaOCalculo.get(i); 
			BigDecimal adjClose;

			if (isFirstDay(i)) {
				adjClose =  calculaEma(diaMapaComum.getClose(), getSMA(i, i + periodo, diasParaOCalculo), multiplicador, i);
			} else {
				adjClose = calculaEma(diaMapaComum.getClose(), dias.get(i - 1).getClose(), multiplicador, i);
			}
			
			dias.add(new DiaOperacao(diaMapaComum.getDate(), adjClose));

		}
		Mapa graficoEMA = new Mapa();
		graficoEMA.setDias(dias);
		
		return graficoEMA;
	}	
	
	private boolean isFirstDay(int i) {
		return i == 0;
	}
	
	private BigDecimal calculaEma(BigDecimal close, BigDecimal emaOntemOuSma, double multiplicador, int i) {
		BigDecimal bigEma = (close.subtract(emaOntemOuSma)).multiply(new BigDecimal(multiplicador)).add(emaOntemOuSma);
		
		return bigEma;
	}

	private double getMultiplicador(int periodo) {
		return (2.0 / (periodo + 1.0));
	}
	
	private BigDecimal getSMA(int inicio, int fim, List<DiaOperacao> diasParaOCalculo) {
		
		List<BigDecimal> fechamentoPorDia = diasParaOCalculo.stream().map(DiaOperacao::getClose)
				.collect(Collectors.toList());
		
		if (fim > fechamentoPorDia.size()) {
			fim = fechamentoPorDia.size();
		}
		List<BigDecimal> subList = fechamentoPorDia.subList(inicio , fim - 1);
		Double average = subList.stream().mapToDouble(BigDecimal::doubleValue).average().getAsDouble();
		return new BigDecimal(average);
	}	
	
}
