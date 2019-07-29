package dev.wellison.santander;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import dev.wellison.santander.domain.Expense;
import dev.wellison.santander.service.ExpenseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ExpenseService expenseService;

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/healthcheck")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().json("{ \"isWorking\" : true }"));
	}

	@Test
	public void testAddExpenseInvalidUser() throws Exception {

		Expense expense = getSampleExpense();

		String requestBody = asJsonString(expense);

		mockMvc.perform(post("/expense/add").with(httpBasic("invalidUser","oooo")).contentType(APPLICATION_JSON).content(requestBody)).andExpect(status().isUnauthorized()).andReturn();
	}

	@Test
	public void testAddExpenseValidUser() throws Exception {

		Expense expense = getSampleExpense();

		String requestBody = asJsonString(expense);
		mockMvc.perform(post("/expense/add")
				.with(httpBasic("admin","admin"))
				.contentType(APPLICATION_JSON)
				.content(requestBody))
				.andExpect(status().isOk());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Expense getSampleExpense() throws ParseException {
		Expense expense = new Expense();
		expense.setDescription("Almoco");
		expense.setValue(46.0);
		expense.setUserCode(Long.valueOf("1234") );
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date data = new Date(sdf.parse("2019-06-10").getTime());
		expense.setDate(data);
		return expense;
	}


}