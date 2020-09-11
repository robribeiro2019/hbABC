package br.infnet.hbABC.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Mapa {
	
	@JsonProperty(value="dia")
	private List<DiaOperacao> dias;

	public List<DiaOperacao> getDias() {
		return dias;
	}

	public void setDias(List<DiaOperacao> dias) {
		this.dias = dias;
	}	
	
}
