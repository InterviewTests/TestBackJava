package br.com.camaroti.alex.rest.api.expense.service;

import java.util.Date;
import java.util.List;

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
	public List<Expense> findByCodUserOrderByDateDesc(int codUser) {
		return new Expense(expenseRepository, categoryClient).findByCodUserOrderByDateDesc(codUser);
	}

	@Override
	public List<Expense> findByCodUserAndDateBetweenOrderByDateDesc(int codUser, Date start, Date end) {
		return new Expense(expenseRepository, categoryClient).findByCodUserAndDateBetweenOrderByDateDesc(codUser, start, end);
	}


}
