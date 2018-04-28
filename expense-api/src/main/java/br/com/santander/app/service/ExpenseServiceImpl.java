package br.com.santander.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.santander.app.converter.ExpenseConverter;
import br.com.santander.app.dto.ExpenseDTO;
import br.com.santander.app.repository.ExpenseRepository;

@Transactional(readOnly = true)
@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;

	@Transactional(readOnly = false)
	@Override
	public ExpenseDTO insert(final ExpenseDTO expenseDTO) {
		return ExpenseConverter.toDTO(expenseRepository.save(ExpenseConverter.fromDTO(expenseDTO)));
	}

	@Override
	public List<ExpenseDTO> findByIdUser(final Long idUser) {
		return ExpenseConverter.toDTO(expenseRepository.findByUserIdAndExpenseDateBeforeOrderByExpenseDate(idUser,
				getLocalDateTimeMinus5Seconds()));
	}

	@Override
	public List<ExpenseDTO> findByFilter(final ExpenseDTO expenseDTO) {
		final LocalDateTime expenseDate = expenseDTO.getExpenseDate();
		return ExpenseConverter.toDTO(expenseRepository.findByUserIdAndExpenseDateBetween(expenseDTO.getIdUser(),
				getLocalDateTimeStartTime(expenseDate), getLocalDateEndTime(expenseDate)));
	}

	public static LocalDateTime getLocalDateTimeMinus5Seconds() {
		return LocalDateTime.now().minusSeconds(5);
	}

	public static LocalDateTime getLocalDateTimeStartTime(final LocalDateTime expenseDate) {
		return LocalDateTime.of(expenseDate.getYear(), expenseDate.getMonth(), expenseDate.getDayOfMonth(), 0, 0, 0);
	}

	public static LocalDateTime getLocalDateEndTime(final LocalDateTime expenseDate) {
		return LocalDateTime.of(expenseDate.getYear(), expenseDate.getMonth(), expenseDate.getDayOfMonth(), 23, 59, 59);
	}

}
