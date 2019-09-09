package com.santander.interview.service;

import com.santander.interview.domain.Expense;
import com.santander.interview.exception.ExpenseException;

import java.text.ParseException;
import java.util.List;

public interface ExpenseService {
    public void addNewExpense(Expense expense);
    public List<Expense> findExpensesByUserCode(long userCode);
    public List<Expense> findExpensesByUserCodeAndDate(long userCode, String date) throws ExpenseException;
    public void updateExpense(String id, Expense expense) throws ExpenseException;
}
