package br.infnet.hbABC.model.dto;

import java.io.Serializable;

import br.infnet.hbABC.model.Quote;

public class QuoteDTO implements Serializable {

	private static final long serialVersionUID = 7873262101482487088L;
	
	private Quote quote;
	private Quote ema9;
	private Quote ema12;
	private Quote ema26;
	private Quote macdLinha;
	private Quote macdHistograma;

	public Quote getQuote() {
		return quote;
	}
	
	public void setQuote(Quote quote) {
		this.quote = quote;
	}
	
	public Quote getEma9() {
		return ema9;
	}
	
	public void setEma9(Quote ema9) {
		this.ema9 = ema9;
	}
	
	public Quote getEma12() {
		return ema12;
	}
	
	public void setEma12(Quote ema12) {
		this.ema12 = ema12;
	}
	
	public Quote getEma26() {
		return ema26;
	}
	
	public void setEma26(Quote ema26) {
		this.ema26 = ema26;
	}
	
	public Quote getMacdLinha() {
		return macdLinha;
	}
	
	public void setMacdLinha(Quote macdLinha) {
		this.macdLinha = macdLinha;
	}
	
	public Quote getMacdHistograma() {
		return macdHistograma;
	}
	
	public void setMacdHistograma(Quote macdHistograma) {
		this.macdHistograma = macdHistograma;
	}
}
