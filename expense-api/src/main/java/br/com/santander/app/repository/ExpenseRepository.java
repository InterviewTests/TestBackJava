package br.com.santander.app.repository;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.santander.app.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{

	List<Expense> findByUserIdAndExpenseDateBeforeOrderByExpenseDate(Long idUser, ZonedDateTime expenseDate);

	@Query("select e from Expense e where TRUNCATE(e.expenseDate) = TRUNCATE(?1)")
	List<Expense> findByDate(Date productDate);
}
