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
	
	
	private Expense expense1, expense2, expense3, expense4;
	private Category category1, category2, category3, category4;
	
	@Before
	public void setUp() {
		category1 = new Category(1, "Food");
		expense1 = new Expense(1, "Mc Lanche Feliz", 25.90, 1, new Date(), category1);
		category2 = new Category(2, "Toys");
		expense2 = new Expense(2, "Mc Lanche Feliz", 15.0, 1, new Date(), null);
		category3 = new Category(3, "Entertainment");
		expense3 = new Expense(3, "Cinema: Avengers: endgame", 35.0, 2, new Date(), category3);
		category4 = new Category(4, "Entertainment");
		expense4 = new Expense(4, "Cinema: Shazam!", 35.0, 1, new Date(), category4);
	
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
				.findFirstByDescriptionContainingIgnoreCaseAndCategoryNotNullOrderByDateDesc(expense2.getDescription())).thenReturn(expense1);
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
