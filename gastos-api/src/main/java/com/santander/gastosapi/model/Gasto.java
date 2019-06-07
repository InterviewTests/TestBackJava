package com.santander.gastosapi.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName = "gasto")
public class Gasto implements Serializable {
	
	@Id
	@Field
	private String id;
	
	@Field
	private String descricao;
	
	@Field
	private Double valor;
	
	@Field
	private int codigousuario;
	
	@Field
	private Date data;
	
	public Gasto() {}
	
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