package br.com.gestao.gastos.model;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;

public class Categorias {

	@PrimaryKey
	private UUID id;
	
	private String Categoria;
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCategoria() {
		return Categoria;
	}

	public void setCategoria(String categoria) {
		Categoria = categoria;
	}

}
