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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * @author Jorge Demetrio
 * @version 1.0
 * @since 29 de mai de 2018
 */
@Entity
@Table(name = "tb_classificacao_mov")
@NamedQueries({
		@NamedQuery(name = ClassificacaoMovimentacao.BUSCA_CLASSIFICACAO_POR_NOME, query = "FROM ClassificacaoMovimentacao c WHERE upper(c.nome) like :busca") })
public class ClassificacaoMovimentacao implements Serializable {

	public final static String BUSCA_CLASSIFICACAO_POR_NOME = "ClassificacaoMovimentacao.buscarClassificacaoPorNome";

	/**
	 * .
	 */
	private static final long serialVersionUID = 3791215255211335155L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable = false, updatable = false, nullable = true)
	private Long id;

	@NotBlank
	@Column(name = "str_nome", insertable = true, updatable = true, nullable = true, unique = false, length = 200)
	private String nome;

	/**
	 * @return the id
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
