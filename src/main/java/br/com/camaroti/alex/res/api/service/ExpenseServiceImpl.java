package br.com.camaroti.alex.res.api.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.camaroti.alex.res.api.model.Expense;
import br.com.camaroti.alex.res.api.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService{

	@Autowired
	private ExpenseRepository repository;
	
	@Override
	public Expense save(Expense expense) {
		return repository.save(expense);
	}

	@Override
	public Expense update(Expense expense) {
		return repository.save(expense);
	}

	@Override
	public void remove(int id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<Expense> findById(int id) {
		return repository.findById(id);
	}

	@Override
	public Iterable<Expense> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Expense> findByCodUserOrderByDateDesc(int codUser) {
		return repository.findByCodUserOrderByDateDesc(codUser);
	}

	@Override
	public List<Expense> findByCodUserAndDateBetweenOrderByDateDesc(int codUser, Date start, Date end) {
		return repository.findByCodUserAndDateBetweenOrderByDateDesc(codUser, start, end);
	}

}
