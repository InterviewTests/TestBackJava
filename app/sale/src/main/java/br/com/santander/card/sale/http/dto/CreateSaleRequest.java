package br.com.santander.card.sale.http.dto;

import lombok.Data;

@Data
public class CreateSaleRequest {

	private String descricao;
	private double valor;
	private long codigousuario;
	private String data;
	
}
