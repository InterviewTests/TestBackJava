package br.com.santander.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.santander.utils.DateUtils;

public class GastoVO implements Serializable{

	private static final long serialVersionUID = -8948650565344019434L;

	@JsonIgnore
	private UUID codigoGasto;
	
	private String descricao;

	private BigDecimal valor;

	private Long codigoUsuario;
	
	private Date data;
	
	public GastoVO() {}

	public GastoVO(UUID codigoGasto, String descricao, BigDecimal valor, Long codigoUsuario,
			Date data) {
		super();
		this.codigoGasto = codigoGasto;
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
	@JsonIgnore
	public Date getDataAsDate() {
		return data;
	}
	
	public String getData() {
		return DateUtils.dateToString(data);
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Date data) {
		this.data = data;
	}
}
