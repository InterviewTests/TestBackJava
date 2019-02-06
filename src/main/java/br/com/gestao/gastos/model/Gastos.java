package br.com.gestao.gastos.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "gastos")
public class Gastos {
	
	@Id
	private ObjectId _id;
	
	private String descricao;
	
	private BigDecimal valor;
	
	private int codigousuario;
	
	private LocalDate data;
	
	public Gastos() {}
	
	public Gastos(ObjectId _id,  String descricao, BigDecimal valor, int codigousuario, LocalDate data) {
		this._id = _id;
		this.descricao = descricao;
		this.valor = valor;
		this.codigousuario = codigousuario;
		this.data = data;
	}
	
	public String getId() {
		return _id.toHexString();
	}
	public void setId(ObjectId _id) {
		this._id = _id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public int getCodigousuario() {
		return codigousuario;
	}
	public void setCodigousuario(int codigousuario) {
		this.codigousuario = codigousuario;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	
}
