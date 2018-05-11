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

import br.com.santander.service.GastoService;
import br.com.santander.vo.CartaoVO;
import br.com.santander.vo.GastoVO;

@RunWith(SpringRunner.class)
@WebMvcTest(value = GastoController.class, secure = false)
public class GastoControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private GastoService gastoService;
	
	CartaoVO cartaoMock = new CartaoVO(1111222255554444L, new GastoVO(UUID.randomUUID(), "Descricao Gasto", new BigDecimal("123.45"), 1234454654L, new Date(1526000468498L)));

	@Test
	public void listarTodosGastosTest() throws Exception {
		ArrayList<CartaoVO> listCartao = new ArrayList<CartaoVO>();
		
		listCartao.add(cartaoMock);
		
		Mockito.when(
				gastoService.findAll()).thenReturn(listCartao);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/gastos").accept(
				MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		
		String expected = "[{\"descricao\":\"Descricao Gasto\",\"valor\":123.45,\"codigoUsuario\":1234454654,\"data\":\"2018-05-11T01:01:08.498+0000\"}]";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
}
