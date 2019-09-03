package com.santander.interview.service;

import com.santander.interview.domain.Expense;

import java.util.List;

public interface ExpenseService {
    public void addNewExpense(Expense expense);
    public List<Expense> getListExpenses();
    public List<Expense> findExpenseByCodigoUsuarioAndData();
    public List<Expense> findExpenseByCodigoUsuario();
    public void updateExpense(Expense expense);
}
