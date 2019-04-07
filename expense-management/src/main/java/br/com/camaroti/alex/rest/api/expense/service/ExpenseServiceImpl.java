package br.com.camaroti.alex.rest.api.expense.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.camaroti.alex.rest.api.expense.client.CategoryClient;
import br.com.camaroti.alex.rest.api.expense.domain.Expense;
import br.com.camaroti.alex.rest.api.expense.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService{

	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Autowired
	private CategoryClient categoryClient;
	
	
	@Override
	public Expense save(Expense expense) throws Exception {
		return new Expense(expenseRepository, categoryClient).save(expense);
	}

	@Override
	public Expense update(Expense expense) {
		return new Expense(expenseRepository, categoryClient).update(expense);
	}

	@Override
	public void remove(int id) {
		new Expense(expenseRepository, categoryClient).remove(id);
	}

	@Override
	public Optional<Expense> findById(int id) {
		return new Expense(expenseRepository, categoryClient).findById(id);
	}

	@Override
	public Iterable<Expense> findAll() {
		return new Expense(expenseRepository, categoryClient).findAll();
	}

	@Override
	public List<Expense> findByCodUserOrderByDateDesc(int codUser) {
		return new Expense(expenseRepository, categoryClient).findByCodUserOrderByDateDesc(codUser);
	}

	@Override
	public List<Expense> findByCodUserAndDateBetweenOrderByDateDesc(int codUser, Date start, Date end) {
		return new Expense(expenseRepository, categoryClient).findByCodUserAndDateBetweenOrderByDateDesc(codUser, start, end);
	}

	@Override
	public Expense findFirstByDescriptionContainingIgnoreCaseAndCategoryNotNullOrderByDateDesc(String description) {
		return new Expense(expenseRepository, categoryClient).findFirstByDescriptionContainingIgnoreCaseAndCategoryNotNullOrderByDateDesc(description);
	}


}
