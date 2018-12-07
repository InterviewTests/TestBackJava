package br.com.santander.card.sale.http.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CreateSaleRequest implements Serializable {
	private String description;
	private double valor;
	private long codigousuario;
	private String data;
	
}
