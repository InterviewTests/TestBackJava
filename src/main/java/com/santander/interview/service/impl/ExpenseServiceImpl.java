package com.santander.interview.service.impl;

import com.santander.interview.domain.Expense;
import com.santander.interview.repository.ExpenseRepository;
import com.santander.interview.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

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
    public List<Expense> findExpenseByCodigoUsuario(long codigoUsuario) {
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
    public List<Expense> findExpenseByCodigoUsuarioAndData(long codigoUsuario, String data) throws ParseException {
        Date startDate = new SimpleDateFormat("ddMMyyyy").parse(data);
        Date endDate = new Date(startDate.getTime() + (1000 * 60 * 60 * 24));
        return expenseRepository.findByCodigoUsuarioAndDataBetween(codigoUsuario, startDate, endDate);
    }

    @Override
    public void updateExpense(Expense expense) {

    }
}
