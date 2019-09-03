package com.santander.interview.service;

import com.santander.interview.domain.Expense;

import java.util.List;

public interface ExpenseService {
    public void addNewExpense(Expense expense);
    public List<Expense> findExpenseByCodigoUsuario(long codigoUsuario);
    public List<Expense> findExpenseByCodigoUsuarioAndData();
    public void updateExpense(Expense expense);
}
