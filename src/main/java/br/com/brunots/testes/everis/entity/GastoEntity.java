package br.com.brunots.testes.everis.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GastoEntity {

	@JsonProperty("_id")
	private Long id;
	@JsonProperty("descricao")
	private String descricao;
	@JsonProperty("valor")
	private Double valor;
	@JsonProperty("codigousuario")
	private Integer codigousuario;
	@JsonProperty("data")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
	private Date data;

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

	@Override
	public String toString() {
		return "GastoEntity [descricao=" + descricao + ", valor=" + valor + ", codigousuario=" + codigousuario
				+ ", data=" + data + "]";
	}

}
