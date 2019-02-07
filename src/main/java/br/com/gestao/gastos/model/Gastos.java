package br.com.gestao.gastos.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "gastos")
public class Gastos {
	
	@Id
	private ObjectId _id;
	
	private ObjectId idcategoria;
	
 	private String descricao;
	
	private BigDecimal valor;
	
	private int codigousuario;
	
	@Indexed
	private LocalDateTime data;
	
	public Gastos() {}
	
	public Gastos(ObjectId _id,  String descricao, BigDecimal valor, int codigousuario, LocalDateTime data, ObjectId idcategoria) {
		this._id = _id;
		this.descricao = descricao;
		this.valor = valor;
		this.codigousuario = codigousuario;
		this.data = data;
		this.idcategoria = idcategoria;
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
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getIdCategoria() {
		return (idcategoria == null) ? null : idcategoria.toHexString();
	}

	public void setIdCategoria(ObjectId idcategoria) {
		this.idcategoria = idcategoria;
	}
	
}
