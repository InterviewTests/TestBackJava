package br.com.resource.testbackjava.data;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Indexed;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.utils.UUIDs;

@Table
public class CreditCardOutgoing {

	@PrimaryKey
	private UUID id;

	private String descricao;
	private Double valor;
	@Indexed
	private Integer codigousuario;
	@Indexed
	private Date data;
	private String categoria;
	
	public CreditCardOutgoing() {
	}

	public CreditCardOutgoing(String descricao, Double valor, Integer codigousuario, Date data) {
		this.id = UUIDs.timeBased();
		this.descricao = descricao;
		this.valor = valor;
		this.codigousuario = codigousuario;
		this.data = data;
	}

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

	@Override
	public String toString() {
		return "CreditCardOutgoing [id=" + id + ", descricao=" + descricao + ", valor=" + valor + ", codigousuario=" + codigousuario + ", data=" + data + ", categoria=" + categoria + "]";
	}

	

}
