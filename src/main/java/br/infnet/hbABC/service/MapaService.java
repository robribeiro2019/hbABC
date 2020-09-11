package br.infnet.hbABC.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.infnet.hbABC.model.Mapa;
import br.infnet.hbABC.util.Util;

@Service
public class MapaService {

	public List<Mapa> getMapas() {
		return Util.leArquivo().getMapas();
	}
}
