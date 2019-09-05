package br.com.santander.gastos.controller.form;

import java.time.LocalDateTime;

import br.com.santander.gastos.model.Gasto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GastoForm {

	@JsonProperty("descricao")
	private String descricao;
	
	@JsonProperty("valor")
	private double valor;
	
	@JsonProperty("codigousuario")
	private Long codigousuario;
	
	@JsonProperty("data")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime data;
	
	public Gasto converter() {
		return new Gasto(this.getCodigoUsuario(), this.getData(), this.getValor(), this.getDescricao(), null);
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
	public Long getCodigoUsuario() {
		return codigousuario;
	}
	public void setCodigoUsuario(Long codigousuario) {
		this.codigousuario = codigousuario;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
}
