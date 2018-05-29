/**
 *
 */
package br.com.santander.cartao.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.santander.cartao.dao.GastosCartaoDAO;
import br.com.santander.cartao.domain.ClassificacaoMovimentacao;
import br.com.santander.cartao.domain.MovimentacaoCartao;
import br.com.santander.cartao.repository.CartaoRepository;
import br.com.santander.cartao.repository.ClassificacaoMovimentacaoRepository;
import br.com.santander.cartao.repository.MovimentacaoCartaoRepository;
import br.com.santander.cartao.repository.UsuarioCartaoRepository;
import br.com.santander.cartao.vo.ClassificacaoMovimentacaoVO;
import br.com.santander.cartao.vo.MovimentacaoCartaoVO;

/**
 * @author Jorge Demetrio
 * @version 1.0
 * @since 29 de mai de 2018
 */
@Service
public class GastosCartaoDAOJPA implements GastosCartaoDAO {

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private ClassificacaoMovimentacaoRepository classificacaoMovimentacaoRepository;

	@Autowired
	private MovimentacaoCartaoRepository movimentacaoCartaoRepository;

	@Autowired
	private UsuarioCartaoRepository usuarioCartaoRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void adicionarGasto(final MovimentacaoCartaoVO movimentacaoCartaoVO) {
		final MovimentacaoCartao movimentacaoCartao = new MovimentacaoCartao();
		movimentacaoCartao.setCartaoUsuario(cartaoRepository.getOne(movimentacaoCartaoVO.getCartaoUsuario().getId()));
		movimentacaoCartao.setData(movimentacaoCartaoVO.getData());
		movimentacaoCartao.setDescricao(movimentacaoCartaoVO.getDescricao());
		movimentacaoCartao.setValor(movimentacaoCartaoVO.getValor());
		movimentacaoCartaoRepository.save(movimentacaoCartao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterarClassificacaoMovimentacao(final MovimentacaoCartaoVO movimentacaoCartaoVO) {

		// Valiaçãoes manuais
		if (movimentacaoCartaoVO == null) {
			throw new RuntimeException("Objeto de gasto nulo");
		}
		if (movimentacaoCartaoVO.getId() == null || movimentacaoCartaoVO.getId() <= 0) {
			throw new RuntimeException("Id de movimentação inválido");
		}

		if (movimentacaoCartaoVO.getClassificacaoMovimentacao() == null) {
			throw new RuntimeException("Objeto classificação nulo");
		}

		if (movimentacaoCartaoVO.getClassificacaoMovimentacao().getId() == null || movimentacaoCartaoVO.getClassificacaoMovimentacao().getId() <= 0) {
			throw new RuntimeException("Id de classificação inválido");
		}

		final MovimentacaoCartao movimentacaoCartao = movimentacaoCartaoRepository.getOne(movimentacaoCartaoVO.getId());

		final ClassificacaoMovimentacao classificacaoMovimentacao = classificacaoMovimentacaoRepository
				.getOne(movimentacaoCartaoVO.getClassificacaoMovimentacao().getId());

		movimentacaoCartao.setClassificacaoMovimentacao(classificacaoMovimentacao);

		movimentacaoCartaoRepository.save(movimentacaoCartao);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ClassificacaoMovimentacaoVO> buscaClassificacaoGasto(final String nome) {

		final List<ClassificacaoMovimentacaoVO> classificacaoMovimentacaoVOs = new ArrayList<ClassificacaoMovimentacaoVO>();

		final List<ClassificacaoMovimentacao> classificacaoMovimentacaos = classificacaoMovimentacaoRepository
				.buscarClassificacaoPorNome(nome.trim().toUpperCase() + "%");

		ClassificacaoMovimentacaoVO classificacaoMovimentacaoVO;

		for (final ClassificacaoMovimentacao classificacaoMovimentacao : classificacaoMovimentacaos) {
			classificacaoMovimentacaoVO = new ClassificacaoMovimentacaoVO();
			BeanUtils.copyProperties(classificacaoMovimentacao, classificacaoMovimentacaoVO);
			classificacaoMovimentacaoVOs.add(classificacaoMovimentacaoVO);
		}

		return classificacaoMovimentacaoVOs;
	}

	/**
	 * @return the cartaoRepository
	 */
	public CartaoRepository getCartaoRepository() {
		return cartaoRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ClassificacaoMovimentacaoVO getClassificacao(final Long idClassificado) {

		final ClassificacaoMovimentacaoVO classificacaoMovimentacaoVO = new ClassificacaoMovimentacaoVO();
		final ClassificacaoMovimentacao classificacaoMovimentacao = classificacaoMovimentacaoRepository.getOne(idClassificado);
		if (classificacaoMovimentacao == null) {
			return null;
		}

		BeanUtils.copyProperties(classificacaoMovimentacao, classificacaoMovimentacaoVO);

		return classificacaoMovimentacaoVO;
	}

	/**
	 * @return the classificacaoMovimentacaoRepository
	 */
	public ClassificacaoMovimentacaoRepository getClassificacaoMovimentacaoRepository() {
		return classificacaoMovimentacaoRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MovimentacaoCartaoVO getGasto(final Long idGasto, final Long idUsuario) {

		final MovimentacaoCartao movimentacaoCartao = movimentacaoCartaoRepository.gastosClienteDetalhe(idUsuario, idGasto);

		MovimentacaoCartaoVO movimentacaoCartaoVO = new MovimentacaoCartaoVO();

		movimentacaoCartaoVO = new MovimentacaoCartaoVO();
		BeanUtils.copyProperties(movimentacaoCartao, movimentacaoCartaoVO);

		if (movimentacaoCartao.getClassificacaoMovimentacao() != null) {
			movimentacaoCartaoVO.setClassificacaoMovimentacao(new ClassificacaoMovimentacaoVO());
			BeanUtils.copyProperties(movimentacaoCartao.getClassificacaoMovimentacao(), movimentacaoCartaoVO.getClassificacaoMovimentacao());
		}

		return movimentacaoCartaoVO;
	}

	/**
	 * @return the movimentacaoCartaoRepository
	 */
	public MovimentacaoCartaoRepository getMovimentacaoCartaoRepository() {
		return movimentacaoCartaoRepository;
	}

	/**
	 * @return the usuarioCartaoRepository
	 */
	public UsuarioCartaoRepository getUsuarioCartaoRepository() {
		return usuarioCartaoRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ClassificacaoMovimentacaoVO gravarClassificado(final ClassificacaoMovimentacaoVO classificacaoMovimentacaoVO) {

		final ClassificacaoMovimentacao classificacaoMovimentacao = new ClassificacaoMovimentacao();

		classificacaoMovimentacao.setNome(classificacaoMovimentacaoVO.getNome());

		classificacaoMovimentacaoRepository.save(classificacaoMovimentacao);
		classificacaoMovimentacaoVO.setId(classificacaoMovimentacao.getId());

		return classificacaoMovimentacaoVO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<MovimentacaoCartaoVO> listaGastos(final Long numeroCartao) {

		final Pageable paginacao = PageRequest.of(0, 10);

		final List<MovimentacaoCartao> result = movimentacaoCartaoRepository.gastosCliente(numeroCartao, paginacao);

		final List<MovimentacaoCartaoVO> retorno = new ArrayList<MovimentacaoCartaoVO>();
		MovimentacaoCartaoVO movimentacaoCartaoVO;

		for (final MovimentacaoCartao m : result) {
			movimentacaoCartaoVO = new MovimentacaoCartaoVO();
			BeanUtils.copyProperties(m, movimentacaoCartaoVO);
			retorno.add(movimentacaoCartaoVO);
		}

		return retorno;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<MovimentacaoCartaoVO> listaGastos(final Long numeroCartao, final Date data) {

		final Calendar calIni = Calendar.getInstance();
		calIni.setTime(data);
		calIni.set(Calendar.HOUR_OF_DAY, 0);
		calIni.set(Calendar.MINUTE, 0);
		calIni.set(Calendar.SECOND, 0);
		calIni.set(Calendar.MILLISECOND, 0);

		final Calendar calFim = Calendar.class.cast(calIni.clone());
		calFim.add(Calendar.DAY_OF_MONTH, 1);

		final List<MovimentacaoCartao> result = movimentacaoCartaoRepository.gastosClienteFiltroData(numeroCartao, calIni.getTime(), calFim.getTime());

		final List<MovimentacaoCartaoVO> retorno = new ArrayList<MovimentacaoCartaoVO>();
		MovimentacaoCartaoVO movimentacaoCartaoVO;

		for (final MovimentacaoCartao m : result) {
			movimentacaoCartaoVO = new MovimentacaoCartaoVO();
			BeanUtils.copyProperties(m, movimentacaoCartaoVO);
			retorno.add(movimentacaoCartaoVO);
		}

		return retorno;
	}

	/**
	 * @param cartaoRepository
	 *            the cartaoRepository to set
	 */
	public void setCartaoRepository(final CartaoRepository cartaoRepository) {
		this.cartaoRepository = cartaoRepository;
	}

	/**
	 * @param classificacaoMovimentacaoRepository
	 *            the classificacaoMovimentacaoRepository to set
	 */
	public void setClassificacaoMovimentacaoRepository(final ClassificacaoMovimentacaoRepository classificacaoMovimentacaoRepository) {
		this.classificacaoMovimentacaoRepository = classificacaoMovimentacaoRepository;
	}

	/**
	 * @param movimentacaoCartaoRepository
	 *            the movimentacaoCartaoRepository to set
	 */
	public void setMovimentacaoCartaoRepository(final MovimentacaoCartaoRepository movimentacaoCartaoRepository) {
		this.movimentacaoCartaoRepository = movimentacaoCartaoRepository;
	}

	/**
	 * @param usuarioCartaoRepository
	 *            the usuarioCartaoRepository to set
	 */
	public void setUsuarioCartaoRepository(final UsuarioCartaoRepository usuarioCartaoRepository) {
		this.usuarioCartaoRepository = usuarioCartaoRepository;
	}

}
