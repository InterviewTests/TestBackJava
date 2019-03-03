package br.com.camaroti.alex.res.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.camaroti.alex.res.api.model.Expense;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Integer> {

	List<Expense>findByCodUserOrderByDateDesc(int codUser);
	List<Expense>findByCodUserAndDateBetweenOrderByDateDesc(int codUser, Date start, Date end);
	
}
