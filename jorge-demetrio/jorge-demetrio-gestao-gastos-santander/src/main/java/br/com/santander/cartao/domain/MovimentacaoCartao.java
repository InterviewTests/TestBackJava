/**
 *
 */
package br.com.santander.cartao.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * @author Jorge Demetrio
 * @version 1.0
 * @since 28 de mai de 2018
 */
@Entity
@Table(name = "tb_movimentacao_cartao")
@NamedQueries({
		@NamedQuery(name = MovimentacaoCartao.GASTOS_CLIENTE, query = "SELECT m FROM MovimentacaoCartao as m JOIN m.cartaoUsuario as c WHERE c.id = :id"),
		@NamedQuery(name = MovimentacaoCartao.GASTOS_CLIENTE_FILTRO, query = "SELECT m FROM MovimentacaoCartao as m JOIN m.cartaoUsuario as c WHERE c.id = :id AND m.data BETWEEN :dataIni AND :dataFim  ORDER BY m.data DESC "),
		@NamedQuery(name = MovimentacaoCartao.GASTOS_CLIENTE_DETALHE, query = "SELECT m FROM MovimentacaoCartao as m JOIN m.cartaoUsuario as c WHERE c.id = :id AND m.id = :gasto ORDER BY m.data DESC "), })
public class MovimentacaoCartao implements Serializable {

	/**
	 * .
	 */
	private static final long serialVersionUID = -7711232348443747096L;

	public static final String GASTOS_CLIENTE = "MovimentacaoCartao.gastosCliente";
	public static final String GASTOS_CLIENTE_FILTRO = "MovimentacaoCartao.gastosClienteFiltroData";
	public static final String GASTOS_CLIENTE_DETALHE = "MovimentacaoCartao.gastosClienteDetalhe";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable = false, updatable = false, nullable = true)
	private Long id;

	@NotBlank
	@Column(name = "txt_descricao", insertable = true, updatable = false, nullable = false, unique = false, length = 2000)
	private String descricao;

	@Column(name = "dt_movimentacao", insertable = true, updatable = true, nullable = false, unique = false)
	private Date data;

	@Column(name = "num_valor", insertable = true, updatable = true, nullable = false, unique = false)
	private Double valor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cartao", insertable = true, updatable = false, nullable = true, unique = false)
	private Cartao cartaoUsuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_classificacao", insertable = true, updatable = false, nullable = true, unique = false)
	private ClassificacaoMovimentacao classificacaoMovimentacao;

	/**
	 * @return the cartaoUsuario
	 */
	public Cartao getCartaoUsuario() {
		return cartaoUsuario;
	}

	/**
	 * @return the classificacaoMovimentacao
	 */
	public ClassificacaoMovimentacao getClassificacaoMovimentacao() {
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
	public void setCartaoUsuario(final Cartao cartaoUsuario) {
		this.cartaoUsuario = cartaoUsuario;
	}

	/**
	 * @param classificacaoMovimentacao
	 *            the classificacaoMovimentacao to set
	 */
	public void setClassificacaoMovimentacao(final ClassificacaoMovimentacao classificacaoMovimentacao) {
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
