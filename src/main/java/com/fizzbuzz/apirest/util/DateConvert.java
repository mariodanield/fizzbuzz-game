package com.fizzbuzz.apirest.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Operaciones con fechas.
 *
 * @author Mario
 */
public class DateConvert {
	static DateFormat HOUR_DATE_FORMAT = new SimpleDateFormat("HH:mm:ss:SSS dd/MM/yyyy");
	
	/**
	 * Convertir date en formato "HH:mm:ss:SSS dd/MM/yyyy"
	 * @param date fecha a formatear
	 * @return cadena de texto en formato "HH:mm:ss:SSS dd/MM/yyyy"
	 */
	public static String formatDateToString(Date date) {
		return HOUR_DATE_FORMAT.format(date);
	}
}