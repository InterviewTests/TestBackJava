package br.com.santander.api.util;


import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ConversorLocalDate implements AttributeConverter<LocalDate, java.sql.Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate localDate) {
		if (localDate != null) {
			return java.sql.Date.valueOf(localDate);
		}
		return null;
	}

	@Override
	public LocalDate convertToEntityAttribute(java.sql.Date databaseValue) {
		if (databaseValue != null) {
			return databaseValue.toLocalDate();
		}
		return null;
	}
}
