package br.com.santander.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.santander.app.model.Expense;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long>{

	List<Expense> findExpensesByUserCodeOrderByExpenseDate(Integer userCode);
}
