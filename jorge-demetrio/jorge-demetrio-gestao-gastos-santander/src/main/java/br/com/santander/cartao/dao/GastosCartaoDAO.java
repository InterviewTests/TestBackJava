/**
 *
 */
package br.com.santander.cartao.dao;

import java.util.Date;
import java.util.List;

import br.com.santander.cartao.vo.ClassificacaoMovimentacaoVO;
import br.com.santander.cartao.vo.MovimentacaoCartaoVO;

/**
 * @author Jorge Demetrio
 * @version 1.0
 * @since 29 de mai de 2018
 */
public interface GastosCartaoDAO {

	/**
	 * Registra as movimentacao de cartões
	 *
	 * @param movimentacaoCartaoVO
	 */
	public void adicionarGasto(final MovimentacaoCartaoVO movimentacaoCartaoVO);

	/**
	 * Salvar Alteração de Classificação do Gasto
	 *
	 * @param movimentacaoCartaoVO
	 */
	public void alterarClassificacaoMovimentacao(final MovimentacaoCartaoVO movimentacaoCartaoVO);

	/**
	 * Lista de de Classificação de gastos
	 *
	 * @param classificacaoMovimentacaoVO
	 * @return
	 */
	public List<ClassificacaoMovimentacaoVO> buscaClassificacaoGasto(String nome);

	/**
	 * Busca classificação de movimentação.
	 *
	 * @param idClassificado
	 * @return
	 */
	public ClassificacaoMovimentacaoVO getClassificacao(Long idClassificado);

	/**
	 * Retorna a o detalhe dos gasto com a classificação
	 *
	 * @param idGasto
	 * @return
	 */
	public MovimentacaoCartaoVO getGasto(Long idGasto, Long idUsuario);

	/**
	 * Clssificação de gasto
	 *
	 * @param classificacaoMovimentacaoVOClassificacaoMovimentacaoVO
	 * @return
	 */
	public ClassificacaoMovimentacaoVO gravarClassificado(final ClassificacaoMovimentacaoVO classificacaoMovimentacaoVO);

	/**
	 * Lista de gastos, limitado a 50 registros
	 *
	 * @param numeroCartao
	 * @return
	 */
	public List<MovimentacaoCartaoVO> listaGastos(final Long numeroCartao);

	/**
	 * Lista de gastos
	 * 
	 * @param numeroCartao
	 * @param data
	 * @return
	 */
	public List<MovimentacaoCartaoVO> listaGastos(final Long numeroCartao, final Date data);
}
