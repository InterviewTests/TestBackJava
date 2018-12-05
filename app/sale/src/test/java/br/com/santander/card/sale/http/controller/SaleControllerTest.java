package br.com.santander.card.sale.http.controller;

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
public class SaleControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void insert_nagativeValue() {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isBadRequest());
	}
	
	@Test
	public void insert_withoutUser() {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isBadRequest());
	}
	
	@Test
	public void insert_withoutCategory() {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isBadRequest());
	}
	
	@Test
	public void insert_withoutDescription() {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isBadRequest());
	}
	
	@Test
	public void insert_withoutDate() {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isBadRequest());
	}
	
	@Test
	public void insert_successful() {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isCreated());
	}
	
	
	
}
