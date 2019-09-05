package com.santander.interview.service;

import com.santander.interview.domain.Expense;

import java.text.ParseException;
import java.util.List;

public interface ExpenseService {
    public void addNewExpense(Expense expense);
    public List<Expense> findExpensesByCodigoUsuario(long codigoUsuario);
    public List<Expense> findExpensesByCodigoUsuarioAndData(long codigoUsuario, String data) throws ParseException;
    public void updateExpense(String id, Expense expense);
}
