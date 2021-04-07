package logic.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {
	
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private Util() {}
	
	public static String parseDate(LocalDate date) {
		return date.format(dtf);
	}
	
	public static LocalDate parseDate(String date) {		
		return LocalDate.parse(date, dtf);
	}

}
