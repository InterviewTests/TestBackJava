/**
 *
 */
package br.com.santander.cartao.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.only;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.santander.cartao.dao.UsuarioDAO;
import br.com.santander.cartao.domain.UsuarioCartao;
import br.com.santander.cartao.repository.UsuarioCartaoRepository;
import br.com.santander.cartao.vo.UsuarioCartaoVO;

/**
 * @author Jorge Demetrio
 * @version 1.0
 * @since 28 de mai de 2018
 */
public class UsuarioDAOJPATest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private UsuarioCartaoRepository usuarioCartaoRepository;

	private UsuarioDAO usuarioDAO;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		usuarioCartaoRepository = mock(UsuarioCartaoRepository.class);
		usuarioDAO = new UsuarioDAOJPA();
		((UsuarioDAOJPA) usuarioDAO).setUsuarioCartaoRepository(usuarioCartaoRepository);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link br.com.santander.cartao.dao.impl.UsuarioDAOJPA#buscarUserByUsuarioAndPass(br.com.santander.cartao.vo.UsuarioCartaoVO)}.
	 */
	@Test
	public void testBuscarUserByUsuarioAndPass() {

		final UsuarioCartao usuarioCartao = new UsuarioCartao();
		final UsuarioCartaoVO parametro = new UsuarioCartaoVO();

		parametro.setSenha("123456789");
		parametro.setUsuario("123456789");
		usuarioCartao.setId(12l);
		usuarioCartao.setNomeUsuario("NOME");
		usuarioCartao.setSenha("SENHA");
		usuarioCartao.setUsuario("USUARIO");

		when(usuarioCartaoRepository.buscaPorUsuarioSenha(parametro.getUsuario(), parametro.getSenha())).thenReturn(usuarioCartao);

		final UsuarioCartaoVO retorno = usuarioDAO.buscarUserByUsuarioAndPass(parametro);

		verify(usuarioCartaoRepository, only()).buscaPorUsuarioSenha(parametro.getUsuario(), parametro.getSenha());

		assertNull(retorno.getSenha());
		assertEquals(usuarioCartao.getNomeUsuario(), retorno.getNomeUsuario());
		assertEquals(usuarioCartao.getUsuario(), retorno.getUsuario());
		assertEquals(usuarioCartao.getId(), retorno.getId());

	}

	/**
	 * Test method for
	 * {@link br.com.santander.cartao.dao.impl.UsuarioDAOJPA#buscarUserByUsuarioAndPass(br.com.santander.cartao.vo.UsuarioCartaoVO)}.
	 */
	@Test
	public void testBuscarUserByUsuarioAndPassNaoAchou() {

		final UsuarioCartao usuarioCartao = null;
		final UsuarioCartaoVO parametro = new UsuarioCartaoVO();

		parametro.setSenha("123456789");
		parametro.setUsuario("123456789");

		when(usuarioCartaoRepository.buscaPorUsuarioSenha(parametro.getUsuario(), parametro.getSenha())).thenReturn(usuarioCartao);

		final UsuarioCartaoVO retorno = usuarioDAO.buscarUserByUsuarioAndPass(parametro);

		verify(usuarioCartaoRepository, only()).buscaPorUsuarioSenha(parametro.getUsuario(), parametro.getSenha());

		assertNull(retorno);

	}

}
