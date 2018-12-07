package br.com.santander.card.client.http.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SaleControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	public void all() throws Exception {
		this.mockMvc.perform(get("/100/sales")).andDo(print()).andExpect(status().isOk());
		
	}
	
	public void all_with_user_notfound() throws Exception {
		this.mockMvc.perform(get("/99999/sales")).andDo(print()).andExpect(status().isNotFound());
		
	}
	
	public void searchByDates() throws Exception {
		this.mockMvc.perform(get("/100/sale?datestart=1997-07-16T19:20+01:00&dateend=2018-07-16T19:20+01:00")).andDo(print()).andExpect(status().isOk());
	}
	
	public void searchByDates_Date_empty() throws Exception {
		this.mockMvc.perform(get("/100/sale?datestart=&dateend=")).andDo(print()).andExpect(status().isBadRequest());
	}
	
	public void searchByDates_Invalid_Date() throws Exception {
		this.mockMvc.perform(get("/100/sale?datestart=2018-07-16T19:20+01:00&dateend=2017-07-16T19:20+01:00")).andDo(print()).andExpect(status().isBadRequest());
	}
	
	public void searchByDates_Invalid_Date_Format() throws Exception {
		this.mockMvc.perform(get("/100/sale?datestart=09/12/17&dateend=09/12/18")).andDo(print()).andExpect(status().isBadRequest());
	}
	
	
}
