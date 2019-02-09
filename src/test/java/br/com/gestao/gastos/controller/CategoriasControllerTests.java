package br.com.gestao.gastos.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.gestao.gastos.GastosApplicationTests;

public class CategoriasControllerTests extends GastosApplicationTests{
	
	private MockMvc mockMvc;

	@Autowired
	private CategoriasController categoriasController;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(categoriasController).build();
	}
	
	@Test
	public void testGETCategoriasController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/categorias/recarga"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
