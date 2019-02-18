package br.com.adslima.aggregates;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.axonframework.commandhandling.model.AggregateNotFoundException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.adslima.aggregate.ExpenseManagementAggregate;
import br.com.adslima.command.AddExpenseManagementCommand;
import br.com.adslima.command.UpdateCategoryExpenseManagementCommand;
import br.com.adslima.dto.ExpenseManagementDTO;
import br.com.adslima.events.ExpenseManagementAddedEvent;
import br.com.adslima.events.ExpenseManagementCategoryUpdatedEvent;
import br.com.adslima.model.ExpenseCategory;

/**
 * 
 * @author andrews.silva
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ExpenseManagamentAggregateTest {

	private FixtureConfiguration<ExpenseManagementAggregate> fixture;

	String id = UUID.randomUUID().toString();
	LocalDateTime date = LocalDateTime.now();

	@Before
	public void setUp() throws Exception {
		fixture = new AggregateTestFixture<>(ExpenseManagementAggregate.class);

	}

	/**
	 * teste o envio e de um Evento
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddExpenseManagementOK() throws Exception {

		ExpenseManagementDTO dto = ObjectExpenseManagement(id, date);

		fixture.given()
				.when(new AddExpenseManagementCommand(dto.getId(), dto.getUserCode(), dto.getDescription(),
						dto.getDate(), dto.getValue(), dto.getCategory()))
				.expectEvents(new ExpenseManagementAddedEvent(dto.getId(), dto.getUserCode(),
						dto.getDescription(), dto.getDate(), dto.getValue(), dto.getCategory()));
	}

	/**
	 * Testa passa de campo passando nulo
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddExpenseManagementFieldFull() throws Exception {

		ExpenseManagementDTO dto = ObjectExpenseManagement(id, date);

		fixture.given()
				.when(new AddExpenseManagementCommand(dto.getId(), null, dto.getDescription(), dto.getDate(),
						dto.getValue(), dto.getCategory()))
				.expectExceptionMessage("UserCode não deve estar vazio ou nulo.");
	}

	/**
	 * Valida lançamento de exception
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAggregateNotFoundException() throws Exception {

		ExpenseManagementDTO dto = ObjectExpenseManagement(id, date);

		fixture.given().when(new UpdateCategoryExpenseManagementCommand(dto.getId(), ExpenseCategory.of("Restaurante")))
				.expectException(AggregateNotFoundException.class);
	}

	/**
	 * Valida a atualização da categoria
	 * 
	 * @throws Exception
	 */
	@Test
	public void testExpenseManagementCategoryUpdatedEvent() throws Exception {

		String id = UUID.randomUUID().toString();
		LocalDateTime date = LocalDateTime.now();

		ExpenseManagementDTO dto = ObjectExpenseManagement(id, date);

		ExpenseManagementAggregate expenseManagement = new ExpenseManagementAggregate();

		expenseManagement.on(new ExpenseManagementAddedEvent(dto.getId(), dto.getUserCode(), dto.getDescription(),
				dto.getDate(), dto.getValue(), dto.getCategory()));
		expenseManagement
				.on(new ExpenseManagementCategoryUpdatedEvent(dto.getId(), ExpenseCategory.of("Restaurante")));

		assertEquals("Restaurante", expenseManagement.getCategory().toString());

	}

	/**
	 * @param id
	 * @param date
	 * @return
	 */
	private ExpenseManagementDTO ObjectExpenseManagement(String id, LocalDateTime date) {
		ExpenseManagementDTO dto = new ExpenseManagementDTO();
		dto.setId(id);
		dto.setUserCode(12345);
		dto.setDescription("ExpenseManagement Test");
		dto.setDate(date);
		dto.setValue(BigDecimal.ONE);
		dto.setCategory(null);
		return dto;
	}

}