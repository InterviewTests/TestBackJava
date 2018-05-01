package br.com.santander.app.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.santander.app.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{

	List<Expense> findByUserCodeAndExpenseDateBefore(Long idUser, LocalDateTime expenseDate);

	List<Expense> findByUserCodeAndExpenseDateBetween(Long idUser, LocalDateTime startDate, LocalDateTime endDate);
}
