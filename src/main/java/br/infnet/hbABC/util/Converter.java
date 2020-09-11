package br.infnet.hbABC.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Converter {
	
	public static LocalDate stringToLocalDate(String strLocalDate) {
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(strLocalDate, formatter);
        
        return localDate;
	}
}
