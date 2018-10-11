package br.com.santandertec.gestaodegastos.models;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Categoria implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nomeCategoria;

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	
}
