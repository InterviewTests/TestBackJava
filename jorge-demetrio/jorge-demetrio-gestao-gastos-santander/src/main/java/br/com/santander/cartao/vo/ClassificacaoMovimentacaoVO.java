/**
 *
 */
package br.com.santander.cartao.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Jorge Demetrio
 * @version 1.0
 * @since 28 de mai de 2018
 */
public class ClassificacaoMovimentacaoVO implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 3791215255211335155L;

	private Long id;

	@NotNull
	@Size(min = 2, max = 20)
	private String nome;

	/**
	 * @return the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(final String nome) {
		this.nome = nome;
	}
}
