/**
 *
 */
package br.com.santander.cartao.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
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
import br.com.santander.cartao.service.LoginService;
import br.com.santander.cartao.vo.UsuarioCartaoVO;

/**
 * @author Jorge Demetrio
 * @version 1.0
 * @since 28 de mai de 2018
 */
public class LoginServiceImplTest {

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

	private LoginService loginService;

	private UsuarioDAO usuarioDAO;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		loginService = new LoginServiceImpl();
		usuarioDAO = mock(UsuarioDAO.class);
		((LoginServiceImpl) loginService).setUsuarioDAO(usuarioDAO);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link br.com.santander.cartao.service.impl.LoginServiceImpl#login(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testLogin() {

		final UsuarioCartaoVO usuarioCartaoVO = new UsuarioCartaoVO();
		when(usuarioDAO.buscarUserByUsuarioAndPass(any(UsuarioCartaoVO.class))).thenReturn(usuarioCartaoVO);

		final String token = loginService.login("USUARIO123456", "SENHA123456789");

		verify(usuarioDAO, only()).buscarUserByUsuarioAndPass(any(UsuarioCartaoVO.class));

		assertNotNull(token);

		// fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link br.com.santander.cartao.service.impl.LoginServiceImpl#login(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testLoginNull() {

		final UsuarioCartaoVO usuarioCartaoVO = null;
		when(usuarioDAO.buscarUserByUsuarioAndPass(any(UsuarioCartaoVO.class))).thenReturn(usuarioCartaoVO);

		final String token = loginService.login("USUARIO123456", "SENHA123456789");

		verify(usuarioDAO, only()).buscarUserByUsuarioAndPass(any(UsuarioCartaoVO.class));
		assertNull(token);

		// fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link br.com.santander.cartao.service.impl.LoginServiceImpl#login(java.lang.String, java.lang.String)}.
	 */
	@Test(expected = RuntimeException.class)
	public void testLoginSenhaErrada() {

		final UsuarioCartaoVO usuarioCartaoVO = new UsuarioCartaoVO();
		when(usuarioDAO.buscarUserByUsuarioAndPass(any(UsuarioCartaoVO.class))).thenReturn(usuarioCartaoVO);

		loginService.login("USUARIO123456", "SE");

		// fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link br.com.santander.cartao.service.impl.LoginServiceImpl#login(java.lang.String, java.lang.String)}.
	 */
	@Test(expected = RuntimeException.class)
	public void testLoginUsuarioErrado() {

		final UsuarioCartaoVO usuarioCartaoVO = new UsuarioCartaoVO();
		when(usuarioDAO.buscarUserByUsuarioAndPass(any(UsuarioCartaoVO.class))).thenReturn(usuarioCartaoVO);

		loginService.login("US", "SENHA123456789");

		// fail("Not yet implemented");
	}

}
