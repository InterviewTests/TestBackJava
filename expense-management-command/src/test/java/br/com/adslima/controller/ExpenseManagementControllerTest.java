package br.com.adslima.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
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

import br.com.adslima.command.AddExpenseManagementCommand;
import br.com.adslima.dto.ExpenseManagementCommunsDTO;

/**
 * 
 * @author andrews.silva
 *
 */
@RunWith(SpringRunner.class)
@SpringBootConfiguration
@ActiveProfiles("test")
public class ExpenseManagementControllerTest {

	private static final Integer USER_CODE = 12345;
	private static final String URL_BASE = "/api-command";

	final String id = UUID.randomUUID().toString();
	final LocalDateTime date = LocalDateTime.now();

	private MockMvc mockMvc;

	@MockBean
	CommandGateway commandGateway;

	@InjectMocks
	ExpenseManagementController expenseManagementController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(expenseManagementController).build();
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddExpenseManagementOK() throws Exception {

		ExpenseManagementCommunsDTO dto = ObjectExpenseManagement(id, date);

		AddExpenseManagementCommand command = new AddExpenseManagementCommand(dto.getId(), dto.getUserCode(),
				dto.getDescription(), dto.getDate(), dto.getValue(), dto.getCategory());

		BDDMockito.given(commandGateway.send(command)).willReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.post(URL_BASE).content(this.getJsonRequisicaoPost())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.userCode").value(dto.getUserCode()))
				.andExpect(jsonPath("$.data.description").value(dto.getDescription()))
				.andExpect(jsonPath("$.data.date").value(dto.getDate()))
				.andExpect(jsonPath("$.data.value").value(dto.getValue()))
				.andExpect(jsonPath("$.data.category").value(dto.getCategory()))
				.andExpect(jsonPath("$.errors").isEmpty()).andDo(print()).andReturn();
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddExpenseManagementNOK() throws Exception {

		ExpenseManagementCommunsDTO dto = ObjectExpenseManagement(id, date);

		AddExpenseManagementCommand command = new AddExpenseManagementCommand(dto.getId(), dto.getUserCode(),
				dto.getDescription(), dto.getDate(), dto.getValue(), dto.getCategory());

		BDDMockito.given(commandGateway.send(command)).willReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.post(URL_BASE).content(this.getJsonRequisicaoPostBad())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andDo(print()).andReturn();
	}

	/**
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	private String getJsonRequisicaoPost() throws JsonProcessingException {
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
	 * @return
	 * @throws JsonProcessingException
	 */
	private String getJsonRequisicaoPostBad() throws JsonProcessingException {
		ExpenseManagementCommunsDTO expenseDto = new ExpenseManagementCommunsDTO();
		expenseDto.setId(null);
		expenseDto.setUserCode(USER_CODE);
		expenseDto.setDescription("ExpenseManagement Test");
		expenseDto.setDate(date);
		expenseDto.setValue(BigDecimal.ONE);
		expenseDto.setCategory(null);

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(expenseDto);
	}

	/**
	 * @param id
	 * @param date
	 * @return
	 */
	private ExpenseManagementCommunsDTO ObjectExpenseManagement(String id, LocalDateTime date) {
		ExpenseManagementCommunsDTO dto = new ExpenseManagementCommunsDTO();
		dto.setId(id);
		dto.setUserCode(12345);
		dto.setDescription("ExpenseManagement Test");
		dto.setDate(null);
		dto.setValue(BigDecimal.ONE);
		dto.setCategory(null);
		return dto;
	}

}
