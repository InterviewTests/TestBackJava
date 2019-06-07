package com.santander.gastosapi.model;

import java.io.Serializable;
import java.util.Date;

public class Gasto implements Serializable {
	
	private Long id;
	
	private String descricao;
	
	private Double valor;
	
	private int codigousuario;
	
	private Date data;
	
	public Gasto() {}
	
	public Gasto(Long id, String descricao, Double valor, int codigousuario) {
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.codigousuario = codigousuario;
	}
	
	public Gasto(String descricao, Double valor, int codigousuario, Date data) {
		this.descricao = descricao;
		this.valor = valor;
		this.codigousuario = codigousuario;
		this.data = data;
	}
	
	public Gasto(String descricao, Double valor, int codigousuario) {
		this.descricao = descricao;
		this.valor = valor;
		this.codigousuario = codigousuario;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}

	public int getCodigousuario() {
		return codigousuario;
	}

	public void setCodigousuario(int codigousuario) {
		this.codigousuario = codigousuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}