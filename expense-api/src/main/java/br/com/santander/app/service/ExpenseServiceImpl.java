package br.com.santander.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.List;

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

	@Transactional(readOnly = false)
	@Override
	public ExpenseDTO insert(final ExpenseDTO expenseDTO) {
		return ExpenseConverter.toDTO(expenseRepository.save(ExpenseConverter.fromDTO(expenseDTO)));
	}

	@Override
	public List<ExpenseDTO> findByIdUser(final Long idUser) {
		List<ExpenseDTO> teste = null;
		try {
			teste = ExpenseConverter.toDTO(expenseRepository.findByDate(new SimpleDateFormat("dd/MM/yyyy").parse("27/04/2018")));
		} catch (final ParseException e) {
			e.printStackTrace();
		}
		return teste;
		//return ExpenseConverter.toDTO(expenseRepository.findByUserIdAndExpenseDateBeforeOrderByExpenseDate(idUser, getZonedDateTimeMinus5Seconds()));
	}

	public static ZonedDateTime getZonedDateTimeMinus5Seconds() {
		final ZonedDateTime dateReturn= ZonedDateTime.now();
		return dateReturn.minusSeconds(5);
	}

}
