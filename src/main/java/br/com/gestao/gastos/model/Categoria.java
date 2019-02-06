package br.com.gestao.gastos.model;

import org.springframework.data.annotation.Id;


public class Categoria {

	@Id
	private String id;
	
	private String categoria;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
