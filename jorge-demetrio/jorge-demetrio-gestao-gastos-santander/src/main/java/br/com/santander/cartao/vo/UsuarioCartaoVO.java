/**
 *
 */
package br.com.santander.cartao.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Jorge Demetrio .
 * @version 1.0
 * @since 28 de mai de 2018
 */
public class UsuarioCartaoVO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7146695606317627600L;

	private Long id;

	private String nomeUsuario;

	@NotNull
	@Size(min = 5, max = 20)
	private String usuario;

	@NotNull
	@Size(min = 8, max = 20)
	private String senha;

	private CartaoVO cartao;

	/**
	 * @return the cartao
	 */
	public CartaoVO getCartao() {
		return cartao;
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
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param cartao
	 *            the cartao to set
	 */
	public void setCartao(final CartaoVO cartao) {
		this.cartao = cartao;
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

	/**
	 * @param senha
	 *            the senha to set
	 */
	public void setSenha(final String senha) {
		this.senha = senha;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

}
