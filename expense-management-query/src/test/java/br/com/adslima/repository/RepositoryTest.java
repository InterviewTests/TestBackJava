package br.com.adslima.repository;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.adslima.events.ExpenseManagementAddedEvent;
import br.com.adslima.model.ExpenseManagement;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Ignore
public class RepositoryTest {

	private static final Integer USER_CODE = 12345;

	private static final int NUMBER_PAR_PAG = 10;

	@Mock
	private ExpenseManagementRepository repository;

	ExpenseManagementAddedEvent event;

	@Before
	public void setUp() throws Exception {

		String id = UUID.randomUUID().toString();
		LocalDateTime date = LocalDateTime.now();

		this.event = this.getExpenseManagement(id, date);
		this.repository.save(new ExpenseManagement(event.getId(), event.getUserCode(), event.getDescription(),
				event.getDate(), event.getValue(), event.getCategory()));
	}

	@After
	public void tearDown() throws Exception {
		this.repository.deleteAll();
	}

	/**
	 * 
	 */
	@Test
	public void testFindExpenseCardByUserCode() {

		List<ExpenseManagement> expenses = this.repository.findExpensesCardsByUserCode(USER_CODE);

		assertEquals(1, expenses.size());
	}

	/**
	 * 
	 */
	@Test
	public void testFindExpenseCardByUserCodePage() {
		
		Page<ExpenseManagement> expenses = this.repository.findExpensesCardsByUserCode(USER_CODE,
				PageRequest.of(0, NUMBER_PAR_PAG, Direction.DESC, "id"));

		assertEquals(1, expenses.getTotalElements());
	}

	private ExpenseManagementAddedEvent getExpenseManagement(String id, LocalDateTime date) {

		ExpenseManagement expense = new ExpenseManagement();
		expense.setId(id);
		expense.setUserCode(USER_CODE);
		expense.setDescription("ExpenseManagement Test");
		expense.setDate(date);
		expense.setValue(BigDecimal.ONE);
		expense.setCategory(null);

		ExpenseManagementAddedEvent event = new ExpenseManagementAddedEvent(expense.getId(), expense.getUserCode(),
				expense.getDescription(), expense.getDate(), expense.getValue(), expense.getCategory());
		return event;
	}

}
