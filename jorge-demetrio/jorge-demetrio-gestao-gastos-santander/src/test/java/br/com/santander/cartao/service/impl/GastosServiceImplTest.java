/**
 *
 */
package br.com.santander.cartao.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.santander.cartao.dao.GastosCartaoDAO;
import br.com.santander.cartao.service.GastosService;
import br.com.santander.cartao.service.LoginService;
import br.com.santander.cartao.vo.CartaoVO;
import br.com.santander.cartao.vo.ClassificacaoMovimentacaoVO;
import br.com.santander.cartao.vo.MovimentacaoCartaoVO;
import br.com.santander.cartao.vo.UsuarioCartaoVO;

/**
 * @author Jorge Demetrio
 * @version 1.0
 * @since 28 de mai de 2018
 */
public class GastosServiceImplTest {

	private GastosService gastosService;
	private GastosCartaoDAO gastosCartaoDAO;
	private LoginService loginService;

	/**
	 *
	 * /**
	 *
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		gastosService = new GastosServiceImpl();
		gastosCartaoDAO = mock(GastosCartaoDAO.class);
		loginService = mock(LoginService.class);
		((GastosServiceImpl) gastosService).setGastosCartaoDAO(gastosCartaoDAO);
		((GastosServiceImpl) gastosService).setLoginService(loginService);
	}

	/**
	 * Test method for
	 * {@link br.com.santander.cartao.service.impl.GastosServiceImpl#adicionarGasto(java.lang.String, java.lang.String, java.lang.Double, java.util.Date)}.
	 */
	@Test
	public void testAdicionarGasto() {
		final String token = "123123";
		final Long idCartao = 3l;
		final Long idUsuario = 2l;
		final String gastoDesc = "Gastos ";
		final Double valor = 100.23d;
		final Date dataGasto = new Date();

		final UsuarioCartaoVO usuarioCartaoVO = new UsuarioCartaoVO();
		usuarioCartaoVO.setId(idUsuario);
		usuarioCartaoVO.setCartao(new CartaoVO());
		usuarioCartaoVO.getCartao().setId(idCartao);

		when(loginService.getUsuarioLogadoByToken(token)).thenReturn(usuarioCartaoVO);

		gastosService.adicionarGasto(token, gastoDesc, valor, dataGasto);

		verify(gastosCartaoDAO, only()).adicionarGasto(any(MovimentacaoCartaoVO.class));

		verify(loginService, only()).getUsuarioLogadoByToken(token);
	}

	/**
	 * Test method for
	 * {@link br.com.santander.cartao.service.impl.GastosServiceImpl#classificarGasto(java.lang.String, java.lang.Long, java.lang.String, java.lang.Long)}.
	 */
	@Test
	public void testClassificarGasto() {
		final String token = "123123";
		final Long idGasto = 1l;
		final Long idClassificado = 5l;
		final Long idCartao = 3l;
		final Long idUsuario = 2l;

		final UsuarioCartaoVO usuarioCartaoVO = new UsuarioCartaoVO();
		usuarioCartaoVO.setId(idUsuario);
		usuarioCartaoVO.setCartao(new CartaoVO());
		usuarioCartaoVO.getCartao().setId(idCartao);
		final ClassificacaoMovimentacaoVO classificacaoMovimentacaoVO = new ClassificacaoMovimentacaoVO();
		final MovimentacaoCartaoVO movimentacaoCartaoVO = new MovimentacaoCartaoVO();

		when(gastosCartaoDAO.getClassificacao(idClassificado)).thenReturn(classificacaoMovimentacaoVO);
		when(gastosCartaoDAO.getGasto(idGasto, idCartao)).thenReturn(movimentacaoCartaoVO);

		when(loginService.getUsuarioLogadoByToken(token)).thenReturn(usuarioCartaoVO);

		gastosService.classificarGasto(token, idGasto, null, idClassificado);

		verify(gastosCartaoDAO).getClassificacao(idClassificado);
		verify(gastosCartaoDAO).getGasto(idGasto, idCartao);
		verify(loginService).getUsuarioLogadoByToken(token);

		verify(gastosCartaoDAO).alterarClassificacaoMovimentacao(any(MovimentacaoCartaoVO.class));
	}

	/**
	 * Test method for
	 * {@link br.com.santander.cartao.service.impl.GastosServiceImpl#classificarGasto(java.lang.String, java.lang.Long, java.lang.String, java.lang.Long)}.
	 */
	@Test
	public void testClassificarGastoCreating() {
		final String token = "123123";
		final Long idGasto = 1l;
		final String nomeClassifica = "123123";
		final Long idCartao = 3l;
		final Long idUsuario = 2l;

		final UsuarioCartaoVO usuarioCartaoVO = new UsuarioCartaoVO();
		usuarioCartaoVO.setId(idUsuario);
		usuarioCartaoVO.setCartao(new CartaoVO());
		usuarioCartaoVO.getCartao().setId(idCartao);

		final MovimentacaoCartaoVO movimentacaoCartaoVO = new MovimentacaoCartaoVO();

		when(gastosCartaoDAO.getGasto(idGasto, idCartao)).thenReturn(movimentacaoCartaoVO);
		when(loginService.getUsuarioLogadoByToken(token)).thenReturn(usuarioCartaoVO);

		gastosService.classificarGasto(token, idGasto, nomeClassifica, null);

		verify(gastosCartaoDAO).gravarClassificado(any(ClassificacaoMovimentacaoVO.class));
		verify(gastosCartaoDAO).getGasto(idGasto, idCartao);
		verify(loginService).getUsuarioLogadoByToken(token);

		verify(gastosCartaoDAO).alterarClassificacaoMovimentacao(any(MovimentacaoCartaoVO.class));
	}

	/**
	 * Test method for
	 * {@link br.com.santander.cartao.service.impl.GastosServiceImpl#gasto(java.lang.String, java.lang.Long)}.
	 */
	@Test
	public void testGasto() {
		final String token = "123123";
		final Long idGasto = 1l;
		final Long idUsuario = 2l;
		final Long idCartao = 3l;
		final MovimentacaoCartaoVO movimentacaoCartaoVO = new MovimentacaoCartaoVO();
		final UsuarioCartaoVO usuarioCartaoVO = new UsuarioCartaoVO();
		usuarioCartaoVO.setId(idUsuario);
		usuarioCartaoVO.setCartao(new CartaoVO());
		usuarioCartaoVO.getCartao().setId(idCartao);

		when(gastosCartaoDAO.getGasto(idGasto, idCartao)).thenReturn(movimentacaoCartaoVO);
		when(loginService.getUsuarioLogadoByToken(token)).thenReturn(usuarioCartaoVO);

		final MovimentacaoCartaoVO restorno = gastosService.gasto(token, idGasto);

		verify(gastosCartaoDAO, only()).getGasto(idGasto, idCartao);
		verify(loginService, only()).getUsuarioLogadoByToken(token);

		assertEquals(movimentacaoCartaoVO, restorno);
	}

	/**
	 * Test method for
	 * {@link br.com.santander.cartao.service.impl.GastosServiceImpl#listaGastos(java.lang.String)}.
	 */
	@Test
	public void testListaGastosString() {
		final String token = "123123";
		final Long idUsuario = 2l;
		final Long idCartao = 3l;
		final List<MovimentacaoCartaoVO> lista = new ArrayList<MovimentacaoCartaoVO>();
		final MovimentacaoCartaoVO movimentacaoCartaoVO = new MovimentacaoCartaoVO();
		final UsuarioCartaoVO usuarioCartaoVO = new UsuarioCartaoVO();
		usuarioCartaoVO.setId(idUsuario);
		usuarioCartaoVO.setCartao(new CartaoVO());
		usuarioCartaoVO.getCartao().setId(idCartao);

		lista.add(movimentacaoCartaoVO);

		when(gastosCartaoDAO.listaGastos(idCartao)).thenReturn(lista);
		when(loginService.getUsuarioLogadoByToken(token)).thenReturn(usuarioCartaoVO);

		final List<MovimentacaoCartaoVO> restorno = gastosService.listaGastos(token);

		verify(gastosCartaoDAO, only()).listaGastos(idCartao);
		verify(loginService, only()).getUsuarioLogadoByToken(token);

		assertEquals(lista, restorno);
		assertEquals(lista.size(), restorno.size());
	}

	/**
	 * Test method for
	 * {@link br.com.santander.cartao.service.impl.GastosServiceImpl#listaGastos(java.lang.String, java.util.Date)}.
	 */
	@Test
	public void testListaGastosStringDate() {
		final String token = "123123";
		final Long idUsuario = 2l;
		final Long idCartao = 3l;
		final List<MovimentacaoCartaoVO> lista = new ArrayList<MovimentacaoCartaoVO>();
		final MovimentacaoCartaoVO movimentacaoCartaoVO = new MovimentacaoCartaoVO();
		final UsuarioCartaoVO usuarioCartaoVO = new UsuarioCartaoVO();
		final Date dt = new Date();

		usuarioCartaoVO.setId(idUsuario);
		usuarioCartaoVO.setCartao(new CartaoVO());
		usuarioCartaoVO.getCartao().setId(idCartao);

		lista.add(movimentacaoCartaoVO);

		when(gastosCartaoDAO.listaGastos(idCartao, dt)).thenReturn(lista);
		when(loginService.getUsuarioLogadoByToken(token)).thenReturn(usuarioCartaoVO);

		final List<MovimentacaoCartaoVO> restorno = gastosService.listaGastos(token, dt);

		verify(gastosCartaoDAO, only()).listaGastos(idCartao, dt);
		verify(loginService, only()).getUsuarioLogadoByToken(token);

		assertEquals(lista, restorno);
		assertEquals(lista.size(), restorno.size());
	}

}
