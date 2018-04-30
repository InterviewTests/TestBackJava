package br.com.santander.api.util;


import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;



public class SerializadorLocalDate extends JsonSerializer<LocalDate> {

	@Override
	public void serialize(LocalDate data, JsonGenerator json, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		json.writeString(data.format(formatter));
	}

}
