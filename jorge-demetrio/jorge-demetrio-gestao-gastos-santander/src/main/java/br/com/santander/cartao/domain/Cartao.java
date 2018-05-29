/**
 *
 */
package br.com.santander.cartao.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * @author Jorge Demetrio
 * @version 1.0
 * @since 29 de mai de 2018
 */
@Entity
@Table(name = "tb_cartao")
public class Cartao implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -209438078966683149L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable = false, updatable = false, nullable = true)
	private Long id;

	@Column(name = "dt_bloqueado", insertable = true, updatable = true, nullable = true, unique = false)
	private Date bloqueado;

	@NotBlank
	@Column(name = "str_nm_usuario", insertable = true, updatable = true, nullable = true, unique = false, length = 150)
	private String nomeUsuario;

	@OneToMany(mappedBy = "cartaoUsuario")
	private List<MovimentacaoCartao> movimentacoes;

	@OneToOne
	@JoinColumn(name = "id_usuario", insertable = true, updatable = true, nullable = true, unique = false)
	private UsuarioCartao usuario;

	public Date getBloqueado() {
		return bloqueado;
	}

	public Long getId() {
		return id;
	}

	/**
	 * @return the movimentacoes.
	 */
	public List<MovimentacaoCartao> getMovimentacoes() {
		return movimentacoes;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	/**
	 * @return the usuario
	 */
	public UsuarioCartao getUsuario() {
		return usuario;
	}

	public void setBloqueado(final Date bloqueado) {
		this.bloqueado = bloqueado;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @param movimentacoes
	 *            the movimentacoes to set
	 */
	public void setMovimentacoes(final List<MovimentacaoCartao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	public void setNomeUsuario(final String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(final UsuarioCartao usuario) {
		this.usuario = usuario;
	}

}
