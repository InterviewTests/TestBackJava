package br.com.santander.card.sale.http.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CreateSaleResponse {
	private int status = 201;
	private String msg = "created";
	private LocalDateTime time = LocalDateTime.now();
}
