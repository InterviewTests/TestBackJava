package dev.wellison.santander;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.wellison.santander.domain.Category;
import dev.wellison.santander.domain.Expense;
import dev.wellison.santander.repository.CategoryRepository;
import dev.wellison.santander.repository.ExpenseRepository;
import dev.wellison.santander.service.CategoryService;
import dev.wellison.santander.service.ExpenseService;
import dev.wellison.santander.util.UtilTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

	@MockBean
	CategoryService categoryService;

	@MockBean
	ExpenseRepository expenseRepository;

	@MockBean
	CategoryRepository categoryRepository;

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/healthcheck")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().json("{ \"isWorking\" : true }"));
	}

	@Test
	public void testAddExpenseInvalidUser() throws Exception {

		Expense expense = getSampleExpense();

		String requestBody = UtilTest.asJsonString(expense);

		mockMvc.perform(post("/expense/add").with(httpBasic("invalidUser","oooo")).contentType(APPLICATION_JSON).content(requestBody)).andExpect(status().isUnauthorized()).andReturn();
	}

	@Test
	public void testAddExpenseValidUser() throws Exception {

		Expense expense = getSampleExpense();

		String requestBody = UtilTest.asJsonString(expense);
		mockMvc.perform(post("/expense/add")
				.with(httpBasic("admin","admin"))
				.contentType(APPLICATION_JSON)
				.content(requestBody))
				.andExpect(status().isOk());
	}

	@Test
	public void getAllExpensesValid() throws Exception {
		mockMvc.perform(get("/expense/findAll")
				.with(httpBasic("admin","admin"))
				.contentType(APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void findExpenseByUserCodeAndDateValid() throws Exception {
		mockMvc.perform(get("/expense/find/1234/2019-02-02")
				.with(httpBasic("user","user"))
				.contentType(APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void findExpenseByUserCodeValid() throws Exception  {
		mockMvc.perform(get("/expense/find/1234")
				.with(httpBasic("user","user"))
				.contentType(APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteExpenseValid() throws Exception  {
		mockMvc.perform(delete("/expense/991ec2ca-ed72-499d-9772-0f7c5cb34a74")
				.with(httpBasic("admin","admin"))
				.contentType(APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void updateExpenseValid() throws Exception  {
		Expense expense = getSampleExpense();
		expense.setId("991ec2ca-ed72-499d-9772-0f7c5cb34a74");
		Category c = new Category("047ac76b-1831-4454-b7ec-55658917a1cf","Automovel");
		expense.setCategory(c);

		Optional<Expense> optionalExpense = Optional.of(expense);
		Mockito.when(expenseRepository.findById(expense.getId())).thenReturn(optionalExpense);
		Mockito.when(expenseRepository.save(expense))
				.thenReturn(expense);
		expenseService.updateExpense(expense);


	}

	@Test
	public void addCategoryAutomaticallyValid() throws Exception  {

		Expense expense = getSampleExpense();
		Category c = new Category();
		c.setDescription("Automovel");
		Category savedCategory = new Category("047ac76b-1831-4454-b7ec-55658917a1cf","Automovel");
		List<Category> list = new ArrayList<>();
		list.add(savedCategory);
		expense.setCategory(savedCategory);

		Mockito.when(categoryRepository.findByDescription(c.getDescription()))
				.thenReturn(list);
		Mockito.when(expenseRepository.save(expense))
				.thenReturn(expense);
		expenseService.updateExpense(expense);


//
//		String requestBody = UtilTest.asJsonString(expense);
//		mockMvc.perform(post("/expense/update")
//				.with(httpBasic("admin","admin"))
//				.contentType(APPLICATION_JSON)
//				.content(requestBody))
//				.andExpect(status().isOk());
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