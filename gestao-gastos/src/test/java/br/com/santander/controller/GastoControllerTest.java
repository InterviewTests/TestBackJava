package br.com.santander.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.santander.service.CartaoService;
import br.com.santander.service.GastoService;
import br.com.santander.service.UsuarioService;
import br.com.santander.vo.GastoVO;

@RunWith(SpringRunner.class)
@WebMvcTest(value = GastoController.class, secure = false)
public class GastoControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private GastoService gastoService;
	
	@MockBean
	private CartaoService cartaoService;
	
	@MockBean
	private UsuarioService usuarioService;
	
	private static final Long NUMERO_CARTAO = 1111222244445555L;
	private static final String DESCRICAO = "Descricao Gasto";
	
	GastoVO gastoMock = new GastoVO(UUID.nameUUIDFromBytes((NUMERO_CARTAO + DESCRICAO).getBytes()), NUMERO_CARTAO, DESCRICAO, new BigDecimal("123.45"), 1234454654L, new Date(1526000468498L));

	@Test
	public void listarTodosGastosTest() throws Exception {
		ArrayList<GastoVO> listGasto = new ArrayList<GastoVO>();
		
		listGasto.add(gastoMock);
		
		Mockito.when(
				gastoService.findAll()).thenReturn(listGasto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/santander/gastos").accept(
				MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		String expected = "[{\"codigoGasto\":\"78a15919-8376-3084-996a-6c5654564531\",\"numeroCartao\":1111222244445555,\"categoria\":null,\"descricao\":\"Descricao Gasto\",\"valor\":123.45,\"codigoUsuario\":1234454654,\"data\":\"2018-05-11T01:01:08.498+0000\"}]";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
}
