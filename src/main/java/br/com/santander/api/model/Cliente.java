package br.com.santander.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="cliente")
public class Cliente{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo_usuario")
	private Long codigoUsuario;

	@Column(name = "nome_cliente", nullable = false, length = 150)
	private String nomeCliente;

	public Long getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Long codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
}