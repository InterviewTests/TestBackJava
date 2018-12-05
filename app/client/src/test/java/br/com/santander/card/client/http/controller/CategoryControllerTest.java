package br.com.santander.card.client.http.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	public void searchByCategory_Found() throws Exception {
		String categoriaExistente = null;
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
	}
	
	public void searchByCategory_Notfound() throws Exception {
		String categoriaInexistente = null;
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isNotFound());
	}
}
