package br.infnet.hbABC.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.infnet.hbABC.model.DiaOperacao;
import br.infnet.hbABC.model.Mapa;
import br.infnet.hbABC.util.Converter;

@Component
public class ReaderCSV {
	
	@Autowired
	private Converter converter;
	
	public Mapa lerArquivoCSV() {
		
		Mapa mapa = new Mapa();
		List<DiaOperacao> dias = new ArrayList<DiaOperacao>();
		DiaOperacao diaOperacao;
		
		BufferedReader br = null;

		try {br = new BufferedReader(new FileReader("MGLU3.SA.csv"));
			String line;
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				
				for (int i = 1; i < 7; i++) {

					if ("null".equals(values[i].trim())) {
						line = br.readLine();
						values = line.split(",");
						break;
					}
				}				
				
				diaOperacao = new DiaOperacao();
				diaOperacao.setDate(converter.stringToLocalDate(values[0]));
				diaOperacao.setOpen(new BigDecimal(values[1]));
				diaOperacao.setHigh(new BigDecimal(values[2]));
				diaOperacao.setLow(new BigDecimal(values[3]));
				diaOperacao.setClose(new BigDecimal(values[4]));
				diaOperacao.setAdjClose(new BigDecimal(values[5]));
				diaOperacao.setVolume(new BigDecimal(values[6]));

				dias.add(diaOperacao);
			}
		} catch (Exception e) {

		}
		
		mapa.setDias(dias);
		
		return mapa;
	}	

}
