package br.com.santander.app.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.santander.app.converter.ExpenseConverter;
import br.com.santander.app.data.repository.CategoryRepository;
import br.com.santander.app.data.repository.ExpenseRepository;
import br.com.santander.app.dto.ExpenseDTO;
import br.com.santander.app.exception.ExpenseNotFoundException;
import br.com.santander.app.exception.OptimisticLockException;
import br.com.santander.app.model.Category;
import br.com.santander.app.model.Expense;

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
	public Page<ExpenseDTO> findExpensesByUserCode(final Long userCode, final Pageable pageable) {
		final Page<Expense> expenses = expenseRepository.findByUserCodeAndExpenseDateBefore(userCode, getLocalDateTimeMinus5Seconds(), pageable);
		if (expenses.getContent().isEmpty()) {
			throw new ExpenseNotFoundException("Expenses not found for user with code: " + userCode);
		}
		return new PageImpl<>(ExpenseConverter.toDTO(expenses.getContent()), pageable,
				expenses.getTotalElements());
	}

	@Override
	public Page<ExpenseDTO> findExpensesByFilter(final ExpenseDTO expenseDTO, final Pageable pageable) {
		final Page<Expense> expenses = expenseRepository.findByUserCodeAndExpenseDateBetween(expenseDTO.getUserCode(),
				getLocalDateTimeStartTime(expenseDTO.getDate()), getLocalDateEndTime(expenseDTO.getDate()), pageable);
		if (expenses.getContent().isEmpty()) {
			throw new ExpenseNotFoundException("Expenses not found for user with code: " + expenseDTO.getUserCode()
			+ " and date: " + expenseDTO.getDate());
		}
		return new PageImpl<>(ExpenseConverter.toDTO(expenses.getContent()), pageable,
				expenses.getTotalElements());
	}

	private Category categorizeExpenses(final String description) {
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
