package br.com.santander.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartaoVO implements Serializable{

	private static final long serialVersionUID = -8234688822182429854L;

	@JsonProperty(value ="numerocartao", required = true)
	private Long numeroCartao;
	
	@JsonProperty(value ="codigousuario", required = true)
	private Long codigoUsuario;
	
	@JsonProperty(value ="datacontrato", required = true)
	private Date dataContrato;
	
	public CartaoVO(Long numeroCartao, Long codigoUsuario, Date dataContrato) {
		super();
		this.numeroCartao = numeroCartao;
		this.codigoUsuario = codigoUsuario;
		this.dataContrato = dataContrato;
	}

	public CartaoVO () {
	}

	/**
	 * @return the numeroCartao
	 */
	public Long getNumeroCartao() {
		return numeroCartao;
	}

	/**
	 * @param numeroCartao the numeroCartao to set
	 */
	public void setNumeroCartao(Long numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	/**
	 * @return the codigoUsuario
	 */
	public Long getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(Long codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	/**
	 * @return the dataContrato
	 */
	public Date getDataContrato() {
		return dataContrato;
	}

	/**
	 * @param dataContrato the dataContrato to set
	 */
	public void setDataContrato(Date dataContrato) {
		this.dataContrato = dataContrato;
	}
	
}
