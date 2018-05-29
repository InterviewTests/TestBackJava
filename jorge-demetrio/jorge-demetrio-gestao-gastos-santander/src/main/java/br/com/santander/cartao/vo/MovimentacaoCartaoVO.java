/**
 *
 */
package br.com.santander.cartao.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Jorge Demetrio
 * @version 1.0
 * @since 28 de mai de 2018
 */
public class MovimentacaoCartaoVO implements Serializable {

	/**
	 * .
	 */
	private static final long serialVersionUID = 5748519059386041892L;

	private Long id;

	@NotNull
	@Size(min = 1, max = 2000)
	private String descricao;

	private Date data;

	@NotNull
	@DecimalMin(value = "0.01")
	private Double valor;

	private CartaoVO cartaoUsuario;

	private ClassificacaoMovimentacaoVO classificacaoMovimentacao;

	/**
	 * @return the cartaoUsuario
	 */
	public CartaoVO getCartaoUsuario() {
		return cartaoUsuario;
	}

	/**
	 * @return the classificacaoMovimentacao
	 */
	public ClassificacaoMovimentacaoVO getClassificacaoMovimentacao() {
		return classificacaoMovimentacao;
	}

	/**
	 * @return the data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the valor
	 */
	public Double getValor() {
		return valor;
	}

	/**
	 * @param cartaoUsuario
	 *            the cartaoUsuario to set
	 */
	public void setCartaoUsuario(final CartaoVO cartaoUsuario) {
		this.cartaoUsuario = cartaoUsuario;
	}

	/**
	 * @param classificacaoMovimentacao
	 *            the classificacaoMovimentacao to set
	 */
	public void setClassificacaoMovimentacao(final ClassificacaoMovimentacaoVO classificacaoMovimentacao) {
		this.classificacaoMovimentacao = classificacaoMovimentacao;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(final Date data) {
		this.data = data;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(final Double valor) {
		this.valor = valor;
	}

}
