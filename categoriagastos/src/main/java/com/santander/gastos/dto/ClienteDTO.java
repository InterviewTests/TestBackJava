package com.santander.gastos.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.santander.gastos.domain.Cliente;

public class ClienteDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String nome;
	
	private String telefone;
	
	private String documento;
	
	private String email;
	
	@JsonIgnore
	private String senha;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public ClienteDTO(Cliente entity) {
		this.setDocumento(entity.getDocumento());
		this.setEmail(entity.getEmail());
		this.setId(entity.getId());
		this.setNome(entity.getNome());
		this.setTelefone(entity.getTelefone());
	}
	
}
