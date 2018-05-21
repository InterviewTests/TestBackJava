package br.com.resource.testbackjava.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import br.com.resource.testbackjava.data.Rank;

public class CreditCardOutgoingVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private UUID id;

	private String descricao;
	private Double valor;
	private Integer codigousuario;
	private Date data;
	private String categoria;
	private Set<Rank> categorias;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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

	public Integer getCodigousuario() {
		return codigousuario;
	}

	public void setCodigousuario(Integer codigousuario) {
		this.codigousuario = codigousuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Set<Rank> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<Rank> categorias) {
		this.categorias = categorias;
	}

	@Override
	public String toString() {
		return "CreditCardOutgoingVO [id=" + id + ", descricao=" + descricao + ", valor=" + valor + ", codigousuario=" + codigousuario + ", data=" + data + ", categorias=" + categorias + "]";
	}
	
	
	
}
