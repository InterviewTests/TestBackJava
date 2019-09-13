package com.santander.interview.service;

import com.santander.interview.domain.Expense;
import com.santander.interview.exception.ExpenseException;

import java.util.List;

public interface ExpenseService {
    public void addNewExpense(Expense newExpense);
    public List<Expense> searchExpensesByUserCode(long userCode);
    public List<Expense> searchExpensesByUserCodeAndDate(long userCode, String date) throws ExpenseException;
    public void updateExpense(String id, Expense newExpense) throws ExpenseException;
}
