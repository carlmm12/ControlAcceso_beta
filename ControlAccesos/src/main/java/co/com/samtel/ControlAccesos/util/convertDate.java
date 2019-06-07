package co.com.samtel.ControlAccesos.util;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class convertDate {

	/*
	 * METODO PARA CONVERTIR UN STRING A UNA FECHA
	 */

	public static Date convertToDate(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date converDate;
		try {
			converDate = formatter.parse(date);
			return converDate;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static LocalDate converToDat(String date) {

		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ldt = LocalDate.parse(date, f);
		System.out.println(ldt.getMonthValue());
		return ldt;
	}

	public static LocalDate converDateCot(Date fecha) {
		String input = fecha.toString();
		DateTimeFormatter f = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss z uuuu").withLocale(Locale.US);
		ZonedDateTime zdt = ZonedDateTime.parse(input, f);
		LocalDate ld = zdt.toLocalDate();
		return ld;
	}

	public static Time converDateTime(Date res) {

		int h = res.getHours();
		int m = res.getMinutes();
		int s = res.getSeconds();
		LocalTime timeConv = LocalTime.of(h, m, s);
		Time tt = Time.valueOf(timeConv);
		
		return tt;
	}
	
	public static Time converDateZero() {

		int h = 00;
		int m = 00;
		int s =00;
		LocalTime timeConv = LocalTime.of(h, m, s);
		Time tt = Time.valueOf(timeConv);
		
		return tt;
	}
	
}
