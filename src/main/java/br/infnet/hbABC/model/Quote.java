package br.infnet.hbABC.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Quote implements Serializable {
	
	private static final long serialVersionUID = -2538998273140450096L;
	
	@JsonProperty(value="dias")
	private List<DiaOperacao> dias;
	
	public Quote() {}

	public Quote(List<DiaOperacao> dias) {
		this.dias = dias;
	}

	public List<DiaOperacao> getDias() {
		return dias;
	}

	public void setDias(List<DiaOperacao> dias) {
		this.dias = dias;
	}
}
