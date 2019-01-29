package br.com.adslima.controller;

import static org.assertj.core.api.Assertions.anyOf;
import static org.hamcrest.CoreMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.adslima.command.AddExpenseManagementCommand;
import br.com.adslima.dto.ExpenseManagementCommunsDTO;

@RunWith(SpringRunner.class)
@SpringBootConfiguration
@ActiveProfiles("test")
public class ExpenseManagementControllerTest {

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

	@Test
	public void hello() throws Exception {

		ExpenseManagementCommunsDTO dto = new ExpenseManagementCommunsDTO();

		AddExpenseManagementCommand command = new AddExpenseManagementCommand(UUID.randomUUID().toString(),
				dto.getUserCode(), dto.getDescription(), dto.getDate(), dto.getValue(), dto.getCategory());

		BDDMockito.given(commandGateway.send(command)).willReturn(null);

		mockMvc.perform(post("/api-command").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string("Hello"));
	}
}
