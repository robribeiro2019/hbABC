package br.infnet.hbABC.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DiaOperacao implements Serializable {
	
	private static final long serialVersionUID = 6824251365994905372L;
	
	private LocalDate  date;
	private BigDecimal open;
	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal close;
	private BigDecimal adjClose;
	private BigDecimal volume;
	
	private BigDecimal ema9;
	private BigDecimal ema12;
	private BigDecimal ema26;
	private BigDecimal macd;
	private BigDecimal macdHistograma;
	
	public DiaOperacao(LocalDate date, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close,
			BigDecimal adjClose, BigDecimal volume, BigDecimal ema9, BigDecimal ema12, BigDecimal ema26,
			BigDecimal macd, BigDecimal macdHistograma) {
		this.date           = date;
		this.open           = open;
		this.high           = high;
		this.low            = low;
		this.close          = close;
		this.adjClose       = adjClose;
		this.volume         = volume;
		this.ema9           = ema9;
		this.ema12          = ema12;
		this.ema26          = ema26;
		this.macd           = macd;
		this.macdHistograma = macdHistograma;
	}
	
	public DiaOperacao() {
		this.ema9           = new BigDecimal(0.0);
		this.ema12          = new BigDecimal(0.0);
		this.ema26          = new BigDecimal(0.0);
		this.macd           = new BigDecimal(0.0);
		this.macdHistograma = new BigDecimal(0.0);
	}	
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public BigDecimal getOpen() {
		return open;
	}
	
	public void setOpen(BigDecimal open) {
		this.open = open;
	}
	
	public BigDecimal getHigh() {
		return high;
	}
	
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	
	public BigDecimal getLow() {
		return low;
	}
	
	public void setLow(BigDecimal low) {
		this.low = low;
	}
	
	public BigDecimal getClose() {
		return close;
	}
	
	public void setClose(BigDecimal close) {
		this.close = close;
	}
	
	public BigDecimal getAdjClose() {
		return adjClose;
	}
	
	public void setAdjClose(BigDecimal adjClose) {
		this.adjClose = adjClose;
	}
	
	public BigDecimal getVolume() {
		return volume;
	}
	
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public BigDecimal getEma9() {
		return ema9;
	}

	public void setEma9(BigDecimal ema9) {
		this.ema9 = ema9;
	}

	public BigDecimal getEma12() {
		return ema12;
	}

	public void setEma12(BigDecimal ema12) {
		this.ema12 = ema12;
	}

	public BigDecimal getEma26() {
		return ema26;
	}

	public void setEma26(BigDecimal ema26) {
		this.ema26 = ema26;
	}

	public BigDecimal getMacd() {
		return macd;
	}

	public void setMacd(BigDecimal macd) {
		this.macd = macd;
	}

	public BigDecimal getMacdHistograma() {
		return macdHistograma;
	}

	public void setMacdHistograma(BigDecimal macdHistograma) {
		this.macdHistograma = macdHistograma;
	}
}
