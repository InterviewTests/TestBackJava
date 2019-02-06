package br.com.gestao.gastos.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categorias")
public class Categoria {

	@Id
	private ObjectId _id;
	
	private String categoria;
	
	public Categoria(ObjectId _id,  String categoria) {
		this._id = _id;
		this.categoria = categoria;
	}
	
	public String getId() {
		return _id.toHexString();
	}

	public void setId(ObjectId _id) {
		this._id = _id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
