package com.santander.gastos.dto;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.santander.gastos.domain.Movimento;

public class GastosDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	int id;
	String descricao;
	double valor;
	String data;
	Long codigoCliente;
	CategoriaDTO categoria;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public GastosDTO() {
		super();
	}
	public CategoriaDTO getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}
	public Long getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(Long codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public GastosDTO(Movimento movimento) {
		
		DateFormat   df = new SimpleDateFormat("yyyy-MM-dd");
		
		this.codigoCliente = movimento.getCartao().getConta().getCliente().getCodigoUsuario();
		this.descricao = movimento.getDescricao();
		this.valor = movimento.getValor();
		this.data = df.format( movimento.getData());
		this.categoria  = new CategoriaDTO( movimento.getCategoria());
		
	}
	
}
