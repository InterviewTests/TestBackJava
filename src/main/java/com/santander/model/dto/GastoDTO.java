package com.santander.model.dto;

public class GastoDTO {
	private String categoria;

	public GastoDTO(String categoria) {
		this.setCategoria(categoria);
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
