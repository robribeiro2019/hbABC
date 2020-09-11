package br.infnet.hbABC.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.infnet.hbABC.model.DiaOperacao;
import br.infnet.hbABC.model.Mapa;
import br.infnet.hbABC.model.dto.MapaDTO;

public class Util {
	
	public static MapaDTO leArquivo() {

		MapaDTO mapaDto = new MapaDTO();
		
		mapaDto.setMapa (ReaderCSV.lerArquivoCSV());
		mapaDto.setMapas(mapaDto.getMapa());

		mapaDto.setEma9 (calculaEma(9, mapaDto.getMapa().getDias()));
		mapaDto.setMapas(mapaDto.getEma9());

		mapaDto.setEma12(calculaEma(12, mapaDto.getMapa().getDias()));
		mapaDto.setMapas(mapaDto.getEma12());

		mapaDto.setEma26(calculaEma(26, mapaDto.getMapa().getDias()));
		mapaDto.setMapas(mapaDto.getEma26());
		
		mapaDto.setMacdLinha(calculaMacdLinha(mapaDto));
		mapaDto.setMapas(mapaDto.getMacdLinha());
		
		mapaDto.setMacdHistograma(calculaMacdHistograma(mapaDto));
		mapaDto.setMapas(mapaDto.getMacdHistograma());
		
		return mapaDto;
	}
	
	public static Mapa calculaMacdLinha(MapaDTO mapaDto) {
		
		Mapa              macdLinha = new Mapa();
		List<DiaOperacao> dias = new ArrayList<>();
		DiaOperacao       dia;
		BigDecimal        close;
		
		for(int i = 0; i < mapaDto.getMapa().getDias().size() ; i++) {
			close = mapaDto.getEma12().getDias().get(i).getClose().subtract(mapaDto.getEma26().getDias().get(i).getClose()).setScale(6, BigDecimal.ROUND_DOWN);
			dia = new DiaOperacao(mapaDto.getMapa().getDias().get(i).getDate(), close);
			dias.add(dia);
		}
		macdLinha.setDias(dias);
		
		return macdLinha;
	}
	
	public static Mapa calculaEma(int periodo, List<DiaOperacao> diasParaOCalculo) {

		List<DiaOperacao> dias          = new ArrayList<>();
		double            multiplicador = getMultiplicador(periodo);
		DiaOperacao       diaMapaComum;
		
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
		return new Mapa(dias);
	}
	
	public static boolean isFirstDay(int i) {
		return i == 0;
	}
	
	public static BigDecimal calculaEma(BigDecimal close, BigDecimal emaOntemOuSma, double multiplicador, int i) {
		BigDecimal bigEma = (close.subtract(emaOntemOuSma)).multiply(new BigDecimal(multiplicador)).add(emaOntemOuSma).setScale(6, BigDecimal.ROUND_DOWN);
		
		return bigEma;
	}

	public static double getMultiplicador(int periodo) {
		return (2.0 / (periodo + 1.0));
	}
	
	public static BigDecimal getSMA(int inicio, int fim, List<DiaOperacao> diasParaOCalculo) {
		
		List<BigDecimal> fechamentoPorDia = diasParaOCalculo.stream().map(DiaOperacao::getClose)
				.collect(Collectors.toList());
		
		if (fim > fechamentoPorDia.size()) {
			fim = fechamentoPorDia.size();
		}
		
		List<BigDecimal> subList = fechamentoPorDia.subList(inicio , fim - 1);
		Double           average = subList.stream().mapToDouble(BigDecimal::doubleValue).average().getAsDouble();
		
		return new BigDecimal(average);
	}
	
	public static Mapa calculaMacdHistograma(MapaDTO mapaDto) {
		
		Mapa              macdHistograma = new Mapa();
		List<DiaOperacao> dias = new ArrayList<>();
		DiaOperacao       dia;
		BigDecimal        close;
		
		Mapa signalLine = calculaEma(9, mapaDto.getMacdLinha().getDias());
		
		for ( int i = 0; i < signalLine.getDias().size(); i++){
			close = mapaDto.getMacdLinha().getDias().get(i).getClose().subtract(signalLine.getDias().get(i).getClose()).setScale(6, BigDecimal.ROUND_DOWN);
			dia = new DiaOperacao(signalLine.getDias().get(i).getDate(), close);
			dias.add(dia);
		}
		macdHistograma.setDias(dias);
		
		return macdHistograma;
	}
}
