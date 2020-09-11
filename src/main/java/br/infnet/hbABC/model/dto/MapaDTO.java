package br.infnet.hbABC.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.infnet.hbABC.model.Mapa;

public class MapaDTO implements Serializable {

	private static final long serialVersionUID = 7873262101482487088L;
	
	private Mapa mapa;
	private Mapa ema9;
	private Mapa ema12;
	private Mapa ema26;
	private Mapa macdLinha;
	private Mapa macdHistograma;
	private List<Mapa> mapas;
	
	public MapaDTO() {
		mapas = new ArrayList<>();
	}

	public Mapa getMapa() {
		return mapa;
	}
	
	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}
	
	public Mapa getEma9() {
		return ema9;
	}
	
	public void setEma9(Mapa ema9) {
		this.ema9 = ema9;
	}
	
	public Mapa getEma12() {
		return ema12;
	}
	
	public void setEma12(Mapa ema12) {
		this.ema12 = ema12;
	}
	
	public Mapa getEma26() {
		return ema26;
	}
	
	public void setEma26(Mapa ema26) {
		this.ema26 = ema26;
	}
	
	public Mapa getMacdLinha() {
		return macdLinha;
	}
	
	public void setMacdLinha(Mapa macdLinha) {
		this.macdLinha = macdLinha;
	}
	
	public Mapa getMacdHistograma() {
		return macdHistograma;
	}
	
	public void setMacdHistograma(Mapa macdHistograma) {
		this.macdHistograma = macdHistograma;
	}
	
	public List<Mapa> getMapas() {
		return mapas;
	}
	
	public void setMapas(Mapa mapa) {
		this.mapas.add(mapa);
	}
}
