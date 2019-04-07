package br.com.camaroti.alex.rest.api.expense.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.camaroti.alex.rest.api.expense.domain.Expense;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Integer> {

	List<Expense>findByCodUserOrderByDateDesc(int codUser);
	List<Expense>findByCodUserAndDateBetweenOrderByDateDesc(int codUser, Date start, Date end);
	Expense findFirstByDescriptionContainingIgnoreCaseAndCategoryNotNullOrderByDateDesc(String description);
	
}
