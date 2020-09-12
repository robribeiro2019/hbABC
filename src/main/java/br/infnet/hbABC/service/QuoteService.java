package br.infnet.hbABC.service;

import org.springframework.stereotype.Service;

import br.infnet.hbABC.model.Quote;
import br.infnet.hbABC.util.Util;

@Service
public class QuoteService {

	public Quote getQuote() {
		return Util.leArquivo();
	}
}
