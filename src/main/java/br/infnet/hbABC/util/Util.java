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
		calculaEma9          (quote);
		calculaEma12         (quote);
		calculaEma26         (quote);
		calculaMacd          (quote);
		calculaMacdHistograma(quote);
		
		return quote;
	}
	
	public static void calculaMacdHistograma(Quote quote) {

		for (int i = 0; i < quote.getDias().size(); i++){
			quote.getDias().get(i)
					.setMacdHistograma(
							quote.getDias().get(i).getMacd().subtract(
									quote.getDias().get(i).getEma9()).add(
											quote.getDias().get(i).getEma26()));
		}
	}
	
	public static void calculaMacd(Quote quote) {
		
		IntStream.range(0, quote.getDias().size()).forEach(
				idx -> quote.getDias().get(idx).setMacd(
						quote.getDias().get(idx).getEma12().subtract(
								quote.getDias().get(idx).getEma26())
						.setScale(6, BigDecimal.ROUND_DOWN)));
	}
	
	public static void calculaEma9(Quote quote) {
		
		List<Double> listEma = calculaEma(9, quote);
		
		IntStream.range(0, listEma.size()).forEach(
				idx -> quote.getDias().get(idx).setEma9(
						new BigDecimal(listEma.get(idx))
						.setScale(6, BigDecimal.ROUND_DOWN)));
	}
	
	public static void calculaEma12(Quote quote) {
		
		List<Double> listEma = calculaEma(12, quote);
		
		IntStream.range(0, listEma.size())
				.forEach(idx -> quote.getDias().get(idx).setEma12(
						new BigDecimal(listEma.get(idx))
						.setScale(6, BigDecimal.ROUND_DOWN)));
	}
	
	public static void calculaEma26(Quote quote) {
		
		List<Double> listEma = calculaEma(26, quote);
		
		IntStream.range(0, listEma.size())
				.forEach(idx -> quote.getDias().get(idx).setEma26(
						new BigDecimal(listEma.get(idx))
						.setScale(6, BigDecimal.ROUND_DOWN)));
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
