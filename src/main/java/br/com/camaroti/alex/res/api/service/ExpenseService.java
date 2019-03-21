package br.com.camaroti.alex.res.api.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import br.com.camaroti.alex.res.api.domain.Expense;

public interface ExpenseService {
	
	Expense save(Expense expense) throws IOException;
	Expense update(Expense expense);
	void remove(int id);
	Optional<Expense> findById(int id);
	Iterable<Expense> findAll();
	List<Expense> findByCodUserOrderByDateDesc(int codUser);
	List<Expense> findByCodUserAndDateBetweenOrderByDateDesc(int codUser, Date start, Date end);
	Expense findFirstByDescriptionContainingIgnoreCaseAndCategoryNotNullOrderByDateDesc(String description);

}
