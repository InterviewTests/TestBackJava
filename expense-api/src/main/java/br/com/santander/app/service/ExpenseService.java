package br.com.santander.app.service;

import java.util.List;

import br.com.santander.app.dto.ExpenseDTO;

public interface ExpenseService {

	ExpenseDTO insert(ExpenseDTO expenseDTO);

	List<ExpenseDTO> findByIdUser(Long idUser);

	List<ExpenseDTO> findByFilter(ExpenseDTO expenseDTO);
}
