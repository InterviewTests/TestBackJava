package br.com.santander.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.santander.app.dto.ExpenseDTO;

public interface ExpenseService {

	ExpenseDTO insert(ExpenseDTO expenseDTO);

	ExpenseDTO update(ExpenseDTO expenseDTO);

	Page<ExpenseDTO> findExpensesByUserCode(Long userCode, Pageable pageable);

	Page<ExpenseDTO> findExpensesByFilter(ExpenseDTO expenseDTO, Pageable pageable);
}
