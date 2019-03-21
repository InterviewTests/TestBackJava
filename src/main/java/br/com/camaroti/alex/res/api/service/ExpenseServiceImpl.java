package br.com.camaroti.alex.res.api.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.camaroti.alex.res.api.domain.Expense;
import br.com.camaroti.alex.res.api.repository.CategoryRepository;
import br.com.camaroti.alex.res.api.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService{

	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Override
	public Expense save(Expense expense) throws IOException {
		return new Expense(expenseRepository, categoryRepository).save(expense);
	}

	@Override
	public Expense update(Expense expense) {
		return new Expense(expenseRepository, categoryRepository).update(expense);
	}

	@Override
	public void remove(int id) {
		new Expense(expenseRepository, categoryRepository).remove(id);
	}

	@Override
	public Optional<Expense> findById(int id) {
		return new Expense(expenseRepository, categoryRepository).findById(id);
	}

	@Override
	public Iterable<Expense> findAll() {
		return new Expense(expenseRepository, categoryRepository).findAll();
	}

	@Override
	public List<Expense> findByCodUserOrderByDateDesc(int codUser) {
		return new Expense(expenseRepository, categoryRepository).findByCodUserOrderByDateDesc(codUser);
	}

	@Override
	public List<Expense> findByCodUserAndDateBetweenOrderByDateDesc(int codUser, Date start, Date end) {
		return new Expense(expenseRepository, categoryRepository).findByCodUserAndDateBetweenOrderByDateDesc(codUser, start, end);
	}

	@Override
	public Expense findFirstByDescriptionContainingIgnoreCaseAndCategoryNotNullOrderByDateDesc(String description) {
		return new Expense(expenseRepository, categoryRepository).findFirstByDescriptionContainingIgnoreCaseAndCategoryNotNullOrderByDateDesc(description);
	}


}
