package br.com.resource.testbackjava.vo;

import java.io.Serializable;

public class LoginVO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer codigoUsuario;
	private String senha;

	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
