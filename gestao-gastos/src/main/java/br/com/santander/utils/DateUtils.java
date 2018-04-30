package br.com.santander.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static final String FORMATO_UTC_BASE = "yyyy-MM-dd";
	public static final String FORMATO_UTC_COMPLEtO = "yyyy-MM-dd HH:mm:ss.SSSZ";
	
	public static Date stringToDate(String dateAsString, String formatoApresentacao) {
		Date dataRetorno = null;
		if (dateAsString != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(formatoApresentacao);
			try {
				dataRetorno = (Date) dateFormat.parse(dateAsString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return dataRetorno;
	}
	
	
	public static Date getDataInicio(Date data) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		

		return calendar.getTime();
	}
	
	public static Date getDataFim(Date data) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		

		return calendar.getTime();
	}
}
