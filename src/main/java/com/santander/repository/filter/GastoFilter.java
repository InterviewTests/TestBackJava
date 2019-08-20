package com.santander.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class GastoFilter {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

}
