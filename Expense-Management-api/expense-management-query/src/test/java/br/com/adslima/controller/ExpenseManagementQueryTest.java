package br.com.adslima.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.adslima.dto.ExpenseManagementCommunsDTO;
import br.com.adslima.event.ExpenseManagementCommunAddedEvent;
import br.com.adslima.model.ExpenseManagement;
import br.com.adslima.repository.ExpenseManagementRepository;

@RunWith(SpringRunner.class)
@SpringBootConfiguration
@ActiveProfiles("test")
public class ExpenseManagementQueryTest {

	private static final Integer USER_CODE = 12345;
	private static final String URL_BASE = "/api-queries";

	final String id = UUID.randomUUID().toString();
	final LocalDateTime date = LocalDateTime.now();

	private MockMvc mockMvc;

	@InjectMocks
	ExpenseManagementQueryController expenseManagementQueryController;

	@MockBean
	private ExpenseManagementRepository repository;

	ExpenseManagementCommunAddedEvent event;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(expenseManagementQueryController).build();

		BDDMockito.given(this.repository.save(Mockito.any(ExpenseManagement.class)))
				.willReturn(new ExpenseManagement());

		this.event = this.getExpenseManagement(id, date);

		this.repository.save(new ExpenseManagement(event.getId(), event.getUserCode(), event.getDescription(),
				event.getDate(), event.getValue(), event.getCategory()));

	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFindAllOK() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE).content(this.getJsonRequestGet())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data").isEmpty()).andExpect(jsonPath("$.errors").isEmpty()).andDo(print())
				.andReturn();

	}

	/**
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	private String getJsonRequestGet() throws JsonProcessingException {
		ExpenseManagementCommunsDTO expenseDto = new ExpenseManagementCommunsDTO();
		expenseDto.setId(id);
		expenseDto.setUserCode(USER_CODE);
		expenseDto.setDescription("ExpenseManagement Test");
		expenseDto.setDate(null);
		expenseDto.setValue(BigDecimal.ONE);
		expenseDto.setCategory(null);

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(expenseDto);
	}

	/**
	 * 
	 * @param id
	 * @param date
	 * @return
	 */
	private ExpenseManagementCommunAddedEvent getExpenseManagement(String id, LocalDateTime date) {

		ExpenseManagement expense = new ExpenseManagement();
		expense.setId(id);
		expense.setUserCode(USER_CODE);
		expense.setDescription("ExpenseManagement Test");
		expense.setDate(date);
		expense.setValue(BigDecimal.ONE);
		expense.setCategory(null);

		ExpenseManagementCommunAddedEvent event = new ExpenseManagementCommunAddedEvent(expense.getId(),
				expense.getUserCode(), expense.getDescription(), expense.getDate(), expense.getValue(),
				expense.getCategory());
		return event;
	}

}
