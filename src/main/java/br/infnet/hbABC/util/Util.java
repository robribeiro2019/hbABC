package br.infnet.hbABC.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.infnet.hbABC.model.DiaOperacao;
import br.infnet.hbABC.model.Quote;
import br.infnet.hbABC.model.dto.QuoteDTO;

public class Util {
	
	public static QuoteDTO leArquivo() {

		QuoteDTO quoteDto = new QuoteDTO();
		
		quoteDto.setQuote(ReaderCSV.lerArquivoCSV());
		quoteDto.setEma9 (calculaEma(9, quoteDto.getQuote().getDias()));
		quoteDto.setEma12(calculaEma(12, quoteDto.getQuote().getDias()));
		quoteDto.setEma26(calculaEma(26, quoteDto.getQuote().getDias()));
		quoteDto.setMacdLinha(calculaMacdLinha(quoteDto));
		quoteDto.setMacdHistograma(calculaMacdHistograma(quoteDto));
		
		return quoteDto;
	}
	
	public static Quote calculaMacdLinha(QuoteDTO quoteDto) {
		
		Quote             macdLinha = new Quote();
		List<DiaOperacao> dias      = new ArrayList<>();
		DiaOperacao       dia;
		BigDecimal        close;
		
		for(int i = 0; i < quoteDto.getQuote().getDias().size() ; i++) {
			close = quoteDto.getEma12().getDias().get(i).getClose().subtract(quoteDto.getEma26().getDias().get(i).getClose()).setScale(6, BigDecimal.ROUND_DOWN);
			dia = new DiaOperacao(quoteDto.getQuote().getDias().get(i).getDate(), close);
			dias.add(dia);
		}
		macdLinha.setDias(dias);
		
		return macdLinha;
	}
	
	public static Quote calculaEma(int periodo, List<DiaOperacao> diasParaOCalculo) {

		List<DiaOperacao> dias          = new ArrayList<>();
		double            multiplicador = getMultiplicador(periodo);
		DiaOperacao       diaQuoteComum;
		
		for (int i = 0; i < diasParaOCalculo.size(); i++) {

			diaQuoteComum = diasParaOCalculo.get(i); 
			BigDecimal adjClose;

			if (isFirstDay(i)) {
				adjClose =  calculaEma(diaQuoteComum.getClose(), getSMA(i, i + periodo, diasParaOCalculo), multiplicador, i);
			} else {
				adjClose = calculaEma(diaQuoteComum.getClose(), dias.get(i - 1).getClose(), multiplicador, i);
			}
			dias.add(new DiaOperacao(diaQuoteComum.getDate(), adjClose));
		}
		return new Quote(dias);
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
	
	public static Quote calculaMacdHistograma(QuoteDTO quoteDto) {
		
		Quote              macdHistograma = new Quote();
		List<DiaOperacao> dias = new ArrayList<>();
		DiaOperacao       dia;
		BigDecimal        close;
		
		Quote signalLine = calculaEma(9, quoteDto.getMacdLinha().getDias());
		
		for ( int i = 0; i < signalLine.getDias().size(); i++){
			close = quoteDto.getMacdLinha().getDias().get(i).getClose().subtract(signalLine.getDias().get(i).getClose()).setScale(6, BigDecimal.ROUND_DOWN);
			dia = new DiaOperacao(signalLine.getDias().get(i).getDate(), close);
			dias.add(dia);
		}
		macdHistograma.setDias(dias);
		
		return macdHistograma;
	}
}
