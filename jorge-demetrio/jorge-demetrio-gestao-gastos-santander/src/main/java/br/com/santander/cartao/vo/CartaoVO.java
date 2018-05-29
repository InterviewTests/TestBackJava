/**
 *
 */
package br.com.santander.cartao.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Jorge Demetrio
 * @version 1.0
 * @since 28 de mai de 2018
 */
public class CartaoVO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -493820014765528088L;

	private Long id;

	private Date bloqueado;

	@NotNull
	@Size(min = 2)
	private String nomeUsuario;

	/**
	 * @return the bloqueado.
	 */
	public Date getBloqueado() {
		return bloqueado;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the nomeUsuario
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	/**
	 * @param bloqueado
	 *            the bloqueado to set
	 */
	public void setBloqueado(final Date bloqueado) {
		this.bloqueado = bloqueado;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @param nomeUsuario
	 *            the nomeUsuario to set
	 */
	public void setNomeUsuario(final String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

}
