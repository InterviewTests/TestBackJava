package br.com.resource.testbackjava.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class Utils {

	
	public static String convertToString(Date value) {
		return convertToString(value, "yyyy-MM-dd");
	}
	
	public static String convertToString(Date value, String pattern) {
		if (value == null) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setLenient(Boolean.FALSE);

		return sdf.format(value);
	}
	
	/**
	 * Converte o parametro hh24mmss hh24:mm:ss, na data indicada.<br>
	 * hh24mmss = 1 -&gt; 00:00:01<br>
	 * hh24mmss = 100 -&gt; 00:01:00<br>
	 * hh24mmss = 100000 -&gt; 10:00:00<br>
	 * 
	 * @param date
	 * @param hh24mmss
	 * @return Date
	 */
	public static Date joinDateTime(Date date, Integer hh24mmss) {

		if (date == null) {
			return null;
		}

		int result = 0;
		if (hh24mmss != null) {
			result = hh24mmss;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		String time = String.format("%06d", result);
		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.substring(0, 2)));
		cal.set(Calendar.MINUTE, Integer.parseInt(time.substring(2, 4)));
		cal.set(Calendar.SECOND, Integer.parseInt(time.substring(4, 6)));

		return cal.getTime();
	}
}
