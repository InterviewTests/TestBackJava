package br.com.gestao.gastos.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.gestao.gastos.GastosApplicationTests;
import br.com.gestao.gastos.model.Gastos;
import br.com.gestao.gastos.service.GastosService;

public class GastosControllerTests extends GastosApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private GastosController gastosController;

	@Autowired
	private GastosService gastosService;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(gastosController).build();
	}

	@Test
	public void testGETGastosController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/gastos/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testGETGastosDetalheController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/gastos/1/5c5bcc6c0000000000000000"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testPOSTGastoController() throws Exception {
		String json = "{\"descricao\":\"Recarga Bilhete Unico\",\"valor\":200,\"codigousuario\":1,\"data\":\"2019-02-08T19:50:00\"}";
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/gastos")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(json))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	public void testPUTAddressController() throws Exception {
		String json = "{\"descricao\":\"Recarga Bilhete Unico\",\"valor\":200,\"codigousuario\":1,\"data\":\"2019-02-08T19:50:00\"}";
		Gastos gasto = (new Gastos(new ObjectId("5c5bcc6c0000000000000000"), "Recarga Bilhete Unico",
				new BigDecimal(200), 1, LocalDateTime.now(), new ObjectId("5c5bcc6c0000000000000001")));
		gastosService.alterarGasto(gasto);
		this.mockMvc.perform(MockMvcRequestBuilders.put("/api/gastos").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

}
