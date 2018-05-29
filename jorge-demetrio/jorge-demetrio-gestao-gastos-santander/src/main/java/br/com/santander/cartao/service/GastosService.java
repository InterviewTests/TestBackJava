/**
 *
 */
package br.com.santander.cartao.service;

import java.util.Date;
import java.util.List;

import br.com.santander.cartao.vo.ClassificacaoMovimentacaoVO;
import br.com.santander.cartao.vo.MovimentacaoCartaoVO;

/**
 * @author Jorge Demetrio.
 * @version 1.0
 * @since 28 de mai de 2018
 */
public interface GastosService {

	/**
	 * Adicionar gastos ao cartão
	 *
	 * @param token
	 * @param descricao
	 * @param valor
	 * @param data
	 */
	public void adicionarGasto(final String token, final String descricao, final Double valor, final Date data);

	/**
	 * Realiza busca like por nome
	 *
	 * @param nome
	 * @return
	 */
	public List<ClassificacaoMovimentacaoVO> buscaClassificao(final String nome);

	/**
	 * Classificar gasto
	 *
	 * @param token
	 * @param idGasto
	 * @param nomeClassifica
	 * @param idClassificado
	 */
	public void classificarGasto(final String token, Long idGasto, final String nomeClassifica, final Long idClassificado);

	/**
	 *
	 * @param token
	 * @param idGasto
	 * @return
	 */
	public MovimentacaoCartaoVO gasto(final String token, final Long idGasto);

	/**
	 * Lista os gastos
	 *
	 * @param codigoUsuario
	 * @return
	 */
	public List<MovimentacaoCartaoVO> listaGastos(final String token);

	/**
	 * Lista os gastos filtrados por data
	 *
	 * @param codigoUsuario
	 * @param data
	 * @return
	 */
	public List<MovimentacaoCartaoVO> listaGastos(final String token, final Date data);
}
