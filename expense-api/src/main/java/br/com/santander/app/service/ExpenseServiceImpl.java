package br.com.santander.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.santander.app.converter.ExpenseConverter;
import br.com.santander.app.dto.ExpenseDTO;
import br.com.santander.app.exception.OptimisticLockException;
import br.com.santander.app.model.Category;
import br.com.santander.app.model.Expense;
import br.com.santander.app.repository.CategoryRepository;
import br.com.santander.app.repository.ExpenseRepository;

@Transactional(readOnly = true)
@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	private static final String OPTIMISTIC_lOCK = "The Expense was update by another transaction.";

	@Transactional(readOnly = false)
	@Override
	public ExpenseDTO insert(final ExpenseDTO expenseDTO) {
		final Expense expense = ExpenseConverter.fromDTO(expenseDTO);
		expense.setCategory(categorizeExpenses(expenseDTO.getDescription()));
		return ExpenseConverter.toDTO(expenseRepository.save(expense));
	}

	@Transactional(readOnly = false)
	@Override
	public ExpenseDTO update(final ExpenseDTO expenseDTO) {
		final Expense expense = ExpenseConverter.fromDTO(expenseDTO);
		expense.setCategory(categorizeExpenses(expenseDTO.getDescription()));
		validateLockOptimistic(expense);
		return ExpenseConverter.toDTO(expenseRepository.save(expense));
	}

	@Override
	public List<ExpenseDTO> findByIdUser(final Long idUser) {
		return ExpenseConverter.toDTO(expenseRepository.findByUserIdAndExpenseDateBeforeOrderByExpenseDate(idUser,
				getLocalDateTimeMinus5Seconds()));
	}

	@Override
	public List<ExpenseDTO> findByFilter(final ExpenseDTO expenseDTO) {
		return ExpenseConverter.toDTO(expenseRepository.findByUserIdAndExpenseDateBetween(expenseDTO.getIdUser(),
				getLocalDateTimeStartTime(expenseDTO.getExpenseDate()),
				getLocalDateEndTime(expenseDTO.getExpenseDate())));
	}

	public Category categorizeExpenses(final String description) {
		Category category = categoryRepository.findByDescriptionEqualsIgnoreCase(description);
		if (description != null && category == null) {
			category = new Category();
			category.setDescription(description);
			return categoryRepository.save(category);
		}
		return category;
	}

	private void validateLockOptimistic(final Expense expense) {
		if (!expenseRepository.findById(expense.getId()).get().getVersion().equals(expense.getVersion())) {
			throw new OptimisticLockException(OPTIMISTIC_lOCK);
		}
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
