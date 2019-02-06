package br.com.gestao.gastos.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
public class Usuario {
	
	@Id
	private ObjectId _id;
	
	private String usuario;
	private String senha;
	
	public Usuario(ObjectId _id,  String usuario, String senha) {
		this._id = _id;
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public String getid() {
		return _id.toHexString();
	}
	public void setid(ObjectId _id) {
		this._id = _id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}	
	
}
