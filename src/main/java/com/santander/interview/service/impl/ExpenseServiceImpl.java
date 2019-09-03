package com.santander.interview.service.impl;

import com.santander.interview.domain.Expense;
import com.santander.interview.repository.ExpenseRepository;
import com.santander.interview.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    ExpenseRepository expenseRepository;

    private String generateUuid() {  return UUID.randomUUID().toString(); }

    @Override
    public void addNewExpense(Expense expense) {
        expense.setId(this.generateUuid());
        expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getListExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public List<Expense> findExpenseByCodigoUsuarioAndData() {
        return null;
    }

    @Override
    public List<Expense> findExpenseByCodigoUsuario() {
        return null;
    }

    @Override
    public void updateExpense(Expense expense) {

    }
}
