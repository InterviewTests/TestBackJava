package com.santander.interview.service;

import com.santander.interview.domain.Expense;

import java.text.ParseException;
import java.util.List;

public interface ExpenseService {
    public void addNewExpense(Expense expense);
    public List<Expense> findExpenseByCodigoUsuario(long codigoUsuario);
    public List<Expense> findExpenseByCodigoUsuarioAndData(long codigoUsuario, String data) throws ParseException;
    public void updateExpense(Expense expense);
}
