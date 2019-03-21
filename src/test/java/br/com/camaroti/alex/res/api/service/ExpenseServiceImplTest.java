package br.com.camaroti.alex.res.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.camaroti.alex.res.api.domain.Expense;
import br.com.camaroti.alex.res.api.repository.ExpenseRepository;

@RunWith(SpringRunner.class)
public class ExpenseServiceImplTest {
	
	private List<Expense> expenses;

	@InjectMocks
	private ExpenseServiceImpl expenseService;

	@Mock
	private ExpenseRepository expenseRepository;
	
	
	@Before
	public void setUp() {
		
		expenses = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			//int codUser = (i / 100) < 1 ? 1 : (i / 100) + 1;
			//double random = ThreadLocalRandom.current().nextDouble(10.0, 100.0);
		//	expenses.add(new Expense(i, "Comida" + i, random, codUser, new Date()));
		}
	}
	

	@Test
	public void insertTenThousandExpense() throws IOException {
		for (Expense expense : expenses) {
			expenseService.save(expense);
			System.out.println(expense);
		}
	}
	

}
