package br.infnet.hbABC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.infnet.hbABC.model.Quote;
import br.infnet.hbABC.service.QuoteService;

@RestController()
@RequestMapping("/char")
public class QuoteController {
	
	@Autowired
	private QuoteService quoteService;
	
	@GetMapping
	public ResponseEntity<Quote> getGrafico(){
		return ResponseEntity.ok(quoteService.getQuote());
	}
}
