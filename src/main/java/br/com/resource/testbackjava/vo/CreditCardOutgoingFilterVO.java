package br.com.resource.testbackjava.vo;

import java.io.Serializable;

public class CreditCardOutgoingFilterVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String descricao;
	private String categoria;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "CreditCardOutgoingFilterVO [id=" + id + ", descricao=" + descricao + ", categoria=" + categoria + "]";
	}

}
