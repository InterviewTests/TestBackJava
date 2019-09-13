package com.santander.interview.service.impl;

import static com.santander.interview.enums.ResponseMessageEnum.*;

import com.santander.interview.domain.Expense;
import com.santander.interview.exception.ExpenseException;
import com.santander.interview.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    Expense expense;

    @Async
    @Override
    public void addNewExpense(Expense newExpense) {
        this.expense.add(newExpense);
    }

    @Override
    public List<Expense> searchExpensesByUserCode(long userCode) {
        return this.expense.searchByUserCode(userCode);
    }

    @Override
    public List<Expense> searchExpensesByUserCodeAndDate(long userCode, String date) throws ExpenseException {
        try {
            return this.expense.searchByUserCodeAndDate(userCode, date);
        } catch(ParseException pe) {
            throw new ExpenseException(HttpStatus.BAD_REQUEST, EXPENSE_BADLY_FORMATTED_DATE);
        }
    }

    @Override
    public void updateExpense(String id, Expense newExpense) throws ExpenseException {
        if (!this.expense.update(id, newExpense)) {
            throw new ExpenseException(HttpStatus.NOT_FOUND, EXPENSE_NOT_FOUND);
        }
    }
}
