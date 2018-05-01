package br.com.santander.app.service;

import java.util.List;

import br.com.santander.app.dto.ExpenseDTO;

public interface ExpenseService {

	ExpenseDTO insert(ExpenseDTO expenseDTO);

	ExpenseDTO update(ExpenseDTO expenseDTO);

	List<ExpenseDTO> findExpensesByUserCode(Long userCode);

	List<ExpenseDTO> findExpensesByFilter(ExpenseDTO expenseDTO);
}
