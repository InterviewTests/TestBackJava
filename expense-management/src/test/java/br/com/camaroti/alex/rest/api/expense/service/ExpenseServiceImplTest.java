package br.com.camaroti.alex.rest.api.expense.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.camaroti.alex.rest.api.expense.builder.CategoryBuilder;
import br.com.camaroti.alex.rest.api.expense.builder.ExpenseBuilder;
import br.com.camaroti.alex.rest.api.expense.client.CategoryClient;
import br.com.camaroti.alex.rest.api.expense.domain.Category;
import br.com.camaroti.alex.rest.api.expense.domain.Expense;
import br.com.camaroti.alex.rest.api.expense.repository.ExpenseRepository;

@RunWith(SpringRunner.class)
public class ExpenseServiceImplTest {

	@InjectMocks
	private ExpenseServiceImpl expenseService;

	@Mock
	private CategoryClient categoryClient;

	@Mock
	private ExpenseRepository expenseRepository;

	private Expense expense1, expense2, expense4;
	private Category category3;

	@Before
	public void setUp() {
		expense1 = ExpenseBuilder.anExpense()
				.withDescription("Mc Lanche Feliz")
				.withValue(25.90)
				.withCategory(
						CategoryBuilder.aCategory()
						.withName("Food").build())
				.build();
		expense2 = ExpenseBuilder.anExpense().withoutCategory().build();
		category3 = CategoryBuilder.aCategory().withCod(2).withName("Entertainment").build();
		expense4 = ExpenseBuilder.anExpense().withCod(4).withDescription("Cinema: Shazam!").withValue(35.0).withCategory(
				CategoryBuilder.aCategory().withCod(3).withName("Entertainment").build()).build(); 

	}

	@Test
	public void saveExpenseWithNewCategory() throws Exception {
		Mockito.when(categoryClient.findByNameIgnoreCase(expense1.getCategory().getName())).thenReturn(null);
		Mockito.when(expenseRepository.save(expense1)).thenReturn(expense1);
		Mockito.when(categoryClient.save(expense1.getCategory().getName())).thenReturn(expense1.getCategory());
		Expense expenseSaved = expenseService.save(expense1);
		assertEquals(expense1, expenseSaved);
		Mockito.verify(categoryClient, Mockito.times(1)).save(expense1.getCategory().getName());
	}

	@Test
	public void saveExpenseBySimilarDescription() throws Exception {
		Mockito.when(expenseRepository
				.findFirstByDescriptionContainingIgnoreCaseAndCategoryNotNullOrderByDateDesc(expense2.getDescription()))
				.thenReturn(expense1);
		Mockito.when(expenseRepository.save(expense2)).thenReturn(expense2);
		Expense expenseSavedWithoutCategory = expenseService.save(expense2);
		assertEquals(expense1.getCategory(), expenseSavedWithoutCategory.getCategory());
	}

	@Test
	public void saveExpenseBySimilarCategoryDescription() throws Exception {
		Mockito.when(categoryClient.findByNameIgnoreCase(expense4.getCategory().getName())).thenReturn(category3);
		expenseService.save(expense4);
		Mockito.verify(categoryClient, Mockito.times(1)).findByNameIgnoreCase(expense4.getCategory().getName());
		assertEquals(category3, expense4.getCategory());
	}

}
