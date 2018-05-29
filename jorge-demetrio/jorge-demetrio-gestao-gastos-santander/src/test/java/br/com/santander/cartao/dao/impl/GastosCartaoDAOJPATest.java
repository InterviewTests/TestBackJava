/**
 *
 */
package br.com.santander.cartao.dao.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.only;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import br.com.santander.cartao.dao.GastosCartaoDAO;
import br.com.santander.cartao.domain.Cartao;
import br.com.santander.cartao.domain.MovimentacaoCartao;
import br.com.santander.cartao.domain.UsuarioCartao;
import br.com.santander.cartao.repository.CartaoRepository;
import br.com.santander.cartao.repository.ClassificacaoMovimentacaoRepository;
import br.com.santander.cartao.repository.MovimentacaoCartaoRepository;
import br.com.santander.cartao.repository.UsuarioCartaoRepository;
import br.com.santander.cartao.vo.CartaoVO;
import br.com.santander.cartao.vo.MovimentacaoCartaoVO;

/**
 * @author Jorge Demetrio
 * @version 1.0
 * @since 28 de mai de 2018
 */
public class GastosCartaoDAOJPATest {

	private CartaoRepository cartaoRepository;
	private ClassificacaoMovimentacaoRepository classificacaoMovimentacaoRepository;
	private MovimentacaoCartaoRepository movimentacaoCartaoRepository;
	private UsuarioCartaoRepository usuarioCartaoRepository;
	private GastosCartaoDAO gastosCartaoDAO;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cartaoRepository = mock(CartaoRepository.class);
		classificacaoMovimentacaoRepository = mock(ClassificacaoMovimentacaoRepository.class);
		movimentacaoCartaoRepository = mock(MovimentacaoCartaoRepository.class);
		usuarioCartaoRepository = mock(UsuarioCartaoRepository.class);
		gastosCartaoDAO = new GastosCartaoDAOJPA();
		((GastosCartaoDAOJPA) gastosCartaoDAO).setCartaoRepository(cartaoRepository);
		((GastosCartaoDAOJPA) gastosCartaoDAO).setClassificacaoMovimentacaoRepository(classificacaoMovimentacaoRepository);
		((GastosCartaoDAOJPA) gastosCartaoDAO).setMovimentacaoCartaoRepository(movimentacaoCartaoRepository);
		((GastosCartaoDAOJPA) gastosCartaoDAO).setUsuarioCartaoRepository(usuarioCartaoRepository);
	}

	/**
	 * Test method for
	 * {@link br.com.santander.cartao.dao.impl.GastosCartaoDAOJPA#adicionarGasto(br.com.santander.cartao.vo.MovimentacaoCartaoVO)}.
	 */
	@Test
	public void testAdicionarGasto() {
		final UsuarioCartao usuarioCartao = new UsuarioCartao();
		final MovimentacaoCartaoVO parametro = new MovimentacaoCartaoVO();
		final Cartao cartao = new Cartao();

		parametro.setData(new Date());
		parametro.setDescricao("Descricao");
		parametro.setValor(102.3d);

		parametro.setCartaoUsuario(new CartaoVO());
		parametro.getCartaoUsuario().setId(1l);

		usuarioCartao.setId(12l);
		usuarioCartao.setNomeUsuario("NOME");
		usuarioCartao.setSenha("SENHA");
		usuarioCartao.setUsuario("USUARIO");

		when(cartaoRepository.getOne(parametro.getCartaoUsuario().getId())).thenReturn(cartao);

		gastosCartaoDAO.adicionarGasto(parametro);

		verify(cartaoRepository, only()).getOne(parametro.getCartaoUsuario().getId());
		verify(movimentacaoCartaoRepository, only()).save(any(MovimentacaoCartao.class));

	}

}
