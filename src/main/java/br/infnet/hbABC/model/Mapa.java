package br.infnet.hbABC.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Mapa implements Serializable {
	
	private static final long serialVersionUID = -2538998273140450096L;
	
	@JsonProperty(value="dia")
	private List<DiaOperacao> dias;
	
	public Mapa() {}

	public Mapa(List<DiaOperacao> dias) {
		this.dias = dias;
	}

	public List<DiaOperacao> getDias() {
		return dias;
	}

	public void setDias(List<DiaOperacao> dias) {
		this.dias = dias;
	}
}
