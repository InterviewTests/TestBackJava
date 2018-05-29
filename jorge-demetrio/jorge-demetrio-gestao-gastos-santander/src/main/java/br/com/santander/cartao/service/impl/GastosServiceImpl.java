/**
 *
 */
package br.com.santander.cartao.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.santander.cartao.dao.GastosCartaoDAO;
import br.com.santander.cartao.service.GastosService;
import br.com.santander.cartao.service.LoginService;
import br.com.santander.cartao.vo.CartaoVO;
import br.com.santander.cartao.vo.ClassificacaoMovimentacaoVO;
import br.com.santander.cartao.vo.MovimentacaoCartaoVO;

/**
 * @author Jorge Demetrio.
 * @version 1.0
 * @since 28 de mai de 2018
 */
@Service
public class GastosServiceImpl implements GastosService {

	private static final Logger LOGGER = Logger.getLogger(GastosServiceImpl.class);

	private static final ValidatorFactory factory;

	static {
		LOGGER.info("Iniciando Objeto Gastos Subindo objetos da validação");
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	private final static Validator validator;

	@Autowired
	GastosCartaoDAO gastosCartaoDAO;

	@Autowired
	LoginService loginService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void adicionarGasto(final String token, final String descricao, final Double valor, final Date data) {

		final MovimentacaoCartaoVO movimentacaoCartaoVO = new MovimentacaoCartaoVO();

		movimentacaoCartaoVO.setCartaoUsuario(new CartaoVO());

		movimentacaoCartaoVO.getCartaoUsuario().setId(this.getCodigoCartaoByToken(token));
		movimentacaoCartaoVO.setData(data);
		movimentacaoCartaoVO.setDescricao(descricao);
		movimentacaoCartaoVO.setValor(valor);

		final Set<ConstraintViolation<MovimentacaoCartaoVO>> constraintViolations = validator.validate(movimentacaoCartaoVO);

		if (!constraintViolations.isEmpty()) {
			final Iterator<ConstraintViolation<MovimentacaoCartaoVO>> it = constraintViolations.iterator();
			String msg = "";
			while (it.hasNext()) {
				msg += it.next().getMessage() + "\n";
			}
			throw new RuntimeException(msg);
		}

		gastosCartaoDAO.adicionarGasto(movimentacaoCartaoVO);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ClassificacaoMovimentacaoVO> buscaClassificao(final String nome) {
		if (nome == null || nome.isEmpty()) {
			LOGGER.error("Erro ao tentar classificar um movimento sem id nem descricação");
			throw new RuntimeException("Erro ao tentar classificar um movimento sem id nem descricação");
		}
		return gastosCartaoDAO.buscaClassificacaoGasto(nome);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void classificarGasto(final String token, final Long idGasto, final String nomeClassifica, final Long idClassificado) {

		ClassificacaoMovimentacaoVO classificacaoMovimentacaoVO;
		final Long idCartao = getCodigoCartaoByToken(token);

		if (idClassificado != null && idClassificado > 0) {
			classificacaoMovimentacaoVO = gastosCartaoDAO.getClassificacao(idClassificado);
		} else {
			if (nomeClassifica == null || nomeClassifica.isEmpty()) {
				LOGGER.error("Erro ao tentar classificar um movimento sem id nem descricação");
				throw new RuntimeException("Erro ao tentar classificar um movimento sem id nem descricação");
			}
			classificacaoMovimentacaoVO = new ClassificacaoMovimentacaoVO();
			classificacaoMovimentacaoVO.setNome(nomeClassifica);
			classificacaoMovimentacaoVO = gastosCartaoDAO.gravarClassificado(classificacaoMovimentacaoVO);
		}
		final MovimentacaoCartaoVO movimentacaoCartaoVO = gastosCartaoDAO.getGasto(idGasto, idCartao);
		movimentacaoCartaoVO.setClassificacaoMovimentacao(classificacaoMovimentacaoVO);

		gastosCartaoDAO.alterarClassificacaoMovimentacao(movimentacaoCartaoVO);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MovimentacaoCartaoVO gasto(final String token, final Long idGasto) {
		return gastosCartaoDAO.getGasto(idGasto, this.getCodigoCartaoByToken(token));
	}

	private Long getCodigoCartaoByToken(final String token) {

		return loginService.getUsuarioLogadoByToken(token).getCartao().getId();
	}

	/**
	 * @return the gastosCartaoDAO
	 */
	public GastosCartaoDAO getGastosCartaoDAO() {
		return gastosCartaoDAO;
	}

	/**
	 * @return the loginService
	 */
	public LoginService getLoginService() {
		return loginService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<MovimentacaoCartaoVO> listaGastos(final String token) {

		return gastosCartaoDAO.listaGastos(this.getCodigoCartaoByToken(token));

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<MovimentacaoCartaoVO> listaGastos(final String token, final Date data) {

		return gastosCartaoDAO.listaGastos(this.getCodigoCartaoByToken(token), data);

	}

	/**
	 * @param gastosCartaoDAO
	 *            the gastosCartaoDAO to set
	 */
	public void setGastosCartaoDAO(final GastosCartaoDAO gastosCartaoDAO) {
		this.gastosCartaoDAO = gastosCartaoDAO;
	}

	/**
	 * @param loginService
	 *            the loginService to set
	 */
	public void setLoginService(final LoginService loginService) {
		this.loginService = loginService;
	}
}
