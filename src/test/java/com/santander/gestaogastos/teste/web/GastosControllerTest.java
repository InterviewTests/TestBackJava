package com.santander.gestaogastos.teste.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.santander.gestaogastos.controller.GastoController;


@RunWith(SpringRunner.class)
@WebMvcTest(GastoController.class)
public class GastosControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private GastoController service;
	
	@Autowired
    private WebApplicationContext wac;

	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	@Test
	public void verifyAllToDoList() throws Exception {
		
	
	}
}
