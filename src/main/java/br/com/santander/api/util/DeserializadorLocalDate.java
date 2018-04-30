package br.com.santander.api.util;


import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;


public class DeserializadorLocalDate extends JsonDeserializer<LocalDate> {

	@Override
	public LocalDate deserialize(JsonParser valor, DeserializationContext conteudo)
			throws IOException, JsonProcessingException {
		String data = valor.getText();
		LocalDate localDate = null;
		if(data != null)
			localDate = LocalDate.parse(data,DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		else 
			localDate = LocalDate.now();
		
		return localDate;
	}

}
