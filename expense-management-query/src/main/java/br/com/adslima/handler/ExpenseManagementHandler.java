package br.com.adslima.handler;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.adslima.exception.ExpenseManagementNotFoundException;
import br.com.adslima.model.Category;
import br.com.adslima.model.ExpenseManagement;
import br.com.adslima.repository.CategoryRepository;
import br.com.adslima.repository.ExpenseManagementRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class ExpenseManagementHandler {

	@Autowired
	ExpenseManagementRepository repository;

	/**
	 * 
	 * @param userCode
	 * @param date
	 * @param pageable
	 * @return
	 */
	public Page<ExpenseManagement> findExpensesCardsByFilter(final Integer userCode, final LocalDateTime date,
			final Pageable pageable) {
		log.info("buscando registros por data para o usuario: " + userCode + " e date: " + date);

		Page<ExpenseManagement> expensesCards = repository.findByUserCodeAndDateBetween(userCode,
				LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 0, 0, 0),
				LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 23, 59, 59), pageable);

		if (expensesCards.getContent().isEmpty()) {
			throw new ExpenseManagementNotFoundException(
					"Não foram encontrados registros por data para o usuario: " + userCode + " e date: " + date);
		}
		return new PageImpl<ExpenseManagement>(expensesCards.getContent(), pageable, expensesCards.getTotalElements());

	}

	/**
	 * 
	 * @param userCode
	 * @param date
	 * @param pageable
	 * @return
	 */
	public Page<ExpenseManagement> findLastExpensesCardsByUserCode(final Integer userCode, final LocalDateTime date,
			final Pageable pageable) {
		log.info("buscando registros por data para o usuario: " + userCode);
		final Page<ExpenseManagement> expensesCards = repository.findByUserCodeAndDateBefore(userCode, date, pageable);

		if (expensesCards.getContent().isEmpty()) {
			throw new ExpenseManagementNotFoundException(
					"Não foram encontrados registros relacionados a esses usuarios: " + userCode);
		}
		return new PageImpl<ExpenseManagement>(expensesCards.getContent(), pageable, expensesCards.getTotalElements());
	}

}
