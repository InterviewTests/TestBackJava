package br.com.santandertec.gestaodegastos.dtos;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class GastoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotBlank(message = "{campo.obrigatorio.gastodto.descricao}")
	private String descricao;
	
	@NotBlank(message = "{campo.obrigatorio.gastodto.valor}")
	private String valor;
	
	@NotBlank(message = "{campo.obrigatorio.gastodto.codigoUsuario}")
	private String codigoUsuario;
	
	@NotBlank(message = "{campo.obrigatorio.gastodto.data}")
	private String data;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

}
