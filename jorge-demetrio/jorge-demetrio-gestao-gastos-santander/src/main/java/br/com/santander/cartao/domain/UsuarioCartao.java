/**
 *
 */
package br.com.santander.cartao.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * @author Jorge Demetrio
 * @version 1.0
 * @since 29 de mai de 2018
 */
@Entity
@Table(name = "tb_usuario")
@NamedQueries({
		@NamedQuery(name = UsuarioCartao.BUSCA_USUARIO_POR_USUARIO_SENHA, query = "SELECT u FROM UsuarioCartao u WHERE upper(u.usuario) = :usuario AND u.senha = :senha") })
public class UsuarioCartao implements Serializable {

	public static final String BUSCA_USUARIO_POR_USUARIO_SENHA = "UsuarioCartao.buscaPorUsuarioSenha";

	/**
	 * .
	 */
	private static final long serialVersionUID = 7146695606317627600L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable = false, updatable = false, nullable = true)
	private Long id;

	@NotBlank
	@Column(name = "str_nm_usuario", insertable = true, updatable = true, nullable = true, unique = false, length = 150)
	private String nomeUsuario;

	@NotBlank
	@Column(name = "str_usuario", insertable = true, updatable = true, nullable = true, unique = true, length = 20)
	private String usuario;

	@NotBlank
	@Column(name = "str_senha", insertable = true, updatable = true, nullable = true, unique = false, length = 60)
	private String senha;

	@OneToOne(mappedBy = "usuario")
	private Cartao cartao;

	/**
	 * @return the cartao
	 */
	public Cartao getCartao() {
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
	public void setCartao(final Cartao cartao) {
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
