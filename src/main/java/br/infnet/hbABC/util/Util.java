package br.infnet.hbABC.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import br.infnet.hbABC.model.Quote;

public class Util {
	
	public static Quote leArquivo() {

		Quote quote = new Quote();
		
		quote = ReaderCSV.lerArquivoCSV();
		calculaEma9           (quote);
		calculaEma12          (quote);
		calculaEma26          (quote);
		//calculaMacdLinha     (quote);
		//calculaMacdHistograma(quote);
		
		return quote;
	}
	
//	public static void calculaMacdLinha(Quote quote) {
//		
//		  double fastEMA = calculaEma(quote, 9).calculate(); 
//		  double slowEMA = calculaEma(quote, 26).calculate(); 
//		  value = fastEMA - slowEMA; 
//		 
//		  return value; 
//		
//		double            multiplicador = getMultiplicador(periodo);
//		List<DiaOperacao> quoteBars     = quote.getDias();
//		
//		for (int i = 1; i < quoteBars.size(); i++) {
//			double ema = quoteBars.get(i).getClose().doubleValue() * multiplicador + quoteBars.get(i - 1).getClose().doubleValue() * (1 - multiplicador);
//
//			switch (periodo) {
//				case 9:
//					quoteBars.get(i).setEma9(new BigDecimal(ema));
//					break;
//				case 12:
//					quoteBars.get(i).setEma12(new BigDecimal(ema));
//					break;
//				case 26:
//					quoteBars.get(i).setEma26(new BigDecimal(ema));
//					break;
//			}
//		}
//	}
	
	public static void calculaEma9(Quote quote) {
		
		List<Double> listEma = calculaEma(9, quote);
		
		IntStream.range(0, listEma.size())
				.forEach(idx -> quote.getDias().get(idx).setEma9(new BigDecimal(listEma.get(idx))));
	}
	
	public static void calculaEma12(Quote quote) {
		
		List<Double> listEma = calculaEma(12, quote);
		
		IntStream.range(0, listEma.size())
				.forEach(idx -> quote.getDias().get(idx).setEma12(new BigDecimal(listEma.get(idx))));
	}
	
	public static void calculaEma26(Quote quote) {
		
		List<Double> listEma = calculaEma(26, quote);
		
		IntStream.range(0, listEma.size())
				.forEach(idx -> quote.getDias().get(idx).setEma26(new BigDecimal(listEma.get(idx))));
	}

	public static List<Double> calculaEma(int periodo, Quote quote) {
		
		double       multiplicador = getMultiplicador(periodo);
		List<Double> listEma       = new ArrayList<Double>();
		
		listEma.add(quote.getDias().get(0).getClose().doubleValue());
		
		for (int i = 1; i < quote.getDias().size(); i++) {
			listEma.add(quote.getDias().get(i).getClose().doubleValue() * multiplicador + listEma.get(i - 1) * (1 - multiplicador));
		}
		
		return listEma;
	}

	public static double getMultiplicador(int periodo) {
		return (2.0 / (periodo + 1.0));
	}
}
