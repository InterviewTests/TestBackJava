package br.com.camaroti.alex.res.api.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import br.com.camaroti.alex.res.api.model.Expense;

public interface ExpenseService {
	
	Expense save(Expense expense);
	Expense update(Expense expense);
	void remove(int id);
	Optional<Expense> findById(int id);
	Iterable<Expense> findAll();
	List<Expense> findByCodUserOrderByDateDesc(int codUser);
	List<Expense> findByCodUserAndDateBetweenOrderByDateDesc(int codUser, Date start, Date end);
	

}
