package br.com.santander.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.santander.app.converter.ExpenseConverter;
import br.com.santander.app.dto.ExpenseDTO;
import br.com.santander.app.repository.ExpenseRepository;

@Transactional(readOnly = true)
@Service
public class ExpenseServiceImpl implements ExpenseService{

	@Autowired
	private ExpenseRepository expenseRepository;

	@Override
	public ExpenseDTO insert(final ExpenseDTO expenseDTO) {
		return ExpenseConverter.toDTO(expenseRepository.save(ExpenseConverter.fromDTO(expenseDTO)));
	}


}
