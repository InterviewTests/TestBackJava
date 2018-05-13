package br.com.santander.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.santander.utils.DateUtils;

public class GastoVO implements Serializable{

	private static final long serialVersionUID = -5988306464921345407L;

	@JsonProperty(value ="codigoGasto", required = false)
	private UUID codigoGasto;
	
	@JsonProperty(value ="numeroCartao", required = false)
	private Long numeroCartao;
	
	@JsonProperty(value ="categoria", required = false)
	private String categoria;
	
	@JsonProperty(value ="descricao", required = true)
	private String descricao;

	@JsonProperty(value ="valor", required = true)
	private BigDecimal valor;

	@JsonProperty(value ="codigoUsuario", required = true)
	private Long codigoUsuario;
	
	@JsonProperty(value ="data", required = true)
	private Date data;
	
	public GastoVO() {}

	public GastoVO(UUID codigoGasto, Long numeroCartao, String descricao, BigDecimal valor, Long codigoUsuario,
			Date data) {
		super();
		this.codigoGasto = codigoGasto;
		this.numeroCartao = numeroCartao;
		this.descricao = descricao;
		this.valor = valor;
		this.codigoUsuario = codigoUsuario;
		this.data = data;
	}

	public GastoVO(UUID codigoGasto, Long numeroCartao, String categoria, String descricao, BigDecimal valor,
			Long codigoUsuario, Date data) {
		super();
		this.codigoGasto = codigoGasto;
		this.numeroCartao = numeroCartao;
		this.categoria = categoria;
		this.descricao = descricao;
		this.valor = valor;
		this.codigoUsuario = codigoUsuario;
		this.data = data;
	}

	/**
	 * @return the codigoGasto
	 */
	public UUID getCodigoGasto() {
		return codigoGasto;
	}

	/**
	 * @param codigoGasto the codigoGasto to set
	 */
	public void setCodigoGasto(UUID codigoGasto) {
		this.codigoGasto = codigoGasto;
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
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
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
	 * @return the data
	 */
	
	public Date getData() {
		return data;
	}
	@JsonIgnore
	public String getDataAsString() {
		return DateUtils.dateToString(data);
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
