package br.infnet.hbABC.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.infnet.hbABC.model.Mapa;
import br.infnet.hbABC.service.MapaService;

@RestController()
@RequestMapping("/chartapi")
public class MapaController {
	
	@Autowired
	private MapaService mapaService;
	
	@CrossOrigin("*")
	@GetMapping("/mapa")
	public ResponseEntity<List<Mapa>> getGrafico(){
		
		List<Mapa> mapas = mapaService.getMapas();
		
		return new ResponseEntity<List<Mapa>>(mapas, HttpStatus.ACCEPTED);
	}	

}
