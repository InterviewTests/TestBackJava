package br.com.camaroti.alex.rest.api.expense.service;

import java.util.Date;
import java.util.List;

import br.com.camaroti.alex.rest.api.expense.domain.Expense;

public interface ExpenseService {
	
	Expense save(Expense expense) throws Exception;
	List<Expense> findByCodUserOrderByDateDesc(int codUser);
	List<Expense> findByCodUserAndDateBetweenOrderByDateDesc(int codUser, Date start, Date end);

}
