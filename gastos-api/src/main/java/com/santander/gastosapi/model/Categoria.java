package com.santander.gastosapi.model;

import java.io.Serializable;

public class Categoria implements Serializable {
	
	private Long id;
	
	private String nome;
	
	public Categoria() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}

