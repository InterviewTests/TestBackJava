package com.santander.interview.service.impl;

import com.santander.interview.domain.Category;
import com.santander.interview.domain.Expense;
import com.santander.interview.repository.CategoryRepository;
import com.santander.interview.repository.ExpenseRepository;
import com.santander.interview.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private String generateUuid() {  return UUID.randomUUID().toString(); }

    private Category automaticCategorization(Expense expense) {
        Category category = expense.getCategory();
        if(category != null && category.getDetail() != null) {
            return categoryRepository.findByDetail(category.getDetail()).get(0);
        }

        return null;
    }

    @Override
    public void addNewExpense(Expense expense) {
        expense.setCategory(this.automaticCategorization(expense));
        expense.setId(this.generateUuid());
        this.expenseRepository.save(expense);
    }

    @Override
    public List<Expense> findExpensesByCodigoUsuario(long codigoUsuario) {
        List<Expense> expenses = expenseRepository.findByCodigoUsuario(codigoUsuario);
        Collections.sort(expenses, new Comparator<Expense>() {
            @Override
            public int compare(Expense expense1, Expense expense2) {
                return expense2.getData().compareTo(expense1.getData());
            }
        });
        return expenses;
    }

    @Override
    public List<Expense> findExpensesByCodigoUsuarioAndData(long codigoUsuario, String data) throws ParseException {
        long oneDayInMilliseconds = 1000 * 60 * 60 * 24;
        Date startDate = new SimpleDateFormat("ddMMyyyy").parse(data);
        Date endDate = new Date(startDate.getTime() + oneDayInMilliseconds);
        return this.expenseRepository.findByCodigoUsuarioAndDataBetween(codigoUsuario, startDate, endDate);
    }

    @Override
    public void updateExpense(String id, Expense expense) {
        this.expenseRepository.findById(id).ifPresent(
                searchResult -> {
                    expense.setId(searchResult.getId());
                    this.expenseRepository.save(expense);
                }
        );
    }
}
