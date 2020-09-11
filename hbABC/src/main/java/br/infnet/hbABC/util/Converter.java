package br.infnet.hbABC.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class Converter {
	
	public LocalDate stringToLocalDate(String strLocalDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(strLocalDate, formatter);
        return localDate;
	}	

}
