package br.com.camaroti.alex.rest.api.expense.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import br.com.camaroti.alex.rest.api.expense.domain.Expense;

public interface ExpenseService {
	
	Expense save(Expense expense) throws Exception;
	Expense update(Expense expense);
	void remove(int id);
	Optional<Expense> findById(int id);
	Iterable<Expense> findAll();
	List<Expense> findByCodUserOrderByDateDesc(int codUser);
	List<Expense> findByCodUserAndDateBetweenOrderByDateDesc(int codUser, Date start, Date end);
	Expense findFirstByDescriptionContainingIgnoreCaseAndCategoryNotNullOrderByDateDesc(String description);

}
