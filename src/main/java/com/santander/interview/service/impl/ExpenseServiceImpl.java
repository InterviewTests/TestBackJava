package com.santander.interview.service.impl;

import static com.santander.interview.enums.ResponseMessageEnum.*;

import com.santander.interview.domain.Category;
import com.santander.interview.domain.Expense;
import com.santander.interview.exception.ExpenseException;
import com.santander.interview.repository.CategoryRepository;
import com.santander.interview.repository.ExpenseRepository;
import com.santander.interview.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    CategoryRepository categoryRepository;

//    private String generateUuid() {  return UUID.randomUUID().toString(); }

    private Category automaticCategorization(Expense expense) {
        Category category = expense.getCategory();
        if(category != null && category.getDetail() != null) {
            List<Category> list = categoryRepository.findByDetail(category.getDetail());
            if (list.size() > 0)
                return list.get(0);
        }

        return null;
    }

    @Override
    public void addNewExpense(Expense expense) {
        Expense newExpense = new Expense(
                expense.getDescription(),
                expense.getValue(),
                expense.getUserCode(),
                expense.getDate()
        );
        newExpense.setCategory(this.automaticCategorization(expense));
        this.expenseRepository.save(newExpense);
    }

    @Override
    public List<Expense> findExpensesByUserCode(long userCode) {
        List<Expense> expenses = expenseRepository.findByUserCode(userCode);
        Collections.sort(expenses, new Comparator<Expense>() {
            @Override
            public int compare(Expense expense1, Expense expense2) {
                return expense2.getDate().compareTo(expense1.getDate());
            }
        });
        return expenses;
    }

    @Override
    public List<Expense> findExpensesByUserCodeAndDate(long userCode, String date) throws ExpenseException {
        long oneDayInMilliseconds = 1000 * 60 * 60 * 24;
        try {
            Date startDate = new SimpleDateFormat("ddMMyyyy").parse(date);
            Date endDate = new Date(startDate.getTime() + oneDayInMilliseconds);
            return this.expenseRepository.findByUserCodeAndDateBetween(userCode, startDate, endDate);
        } catch(ParseException pe) {
            throw new ExpenseException(HttpStatus.BAD_REQUEST, EXPENSE_BADLY_FORMATTED_DATE);
        }
    }

    @Override
    public void updateExpense(String id, Expense expense) throws ExpenseException {
        Optional<Expense> existExpense = this.expenseRepository.findById(id);
        if(existExpense.isPresent()) {
            Expense expenseFound = existExpense.get();
            expense.setId(expenseFound.getId());
            expense.setCategory(this.automaticCategorization(expense));
            this.expenseRepository.save(expense);
        } else {
            throw new ExpenseException(HttpStatus.NOT_FOUND, EXPENSE_NOT_FOUND);
        }
    }
}
