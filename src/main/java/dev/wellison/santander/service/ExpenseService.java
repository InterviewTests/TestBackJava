package dev.wellison.santander.service;

import dev.wellison.santander.domain.Expense;

import java.text.ParseException;
import java.util.List;

public interface ExpenseService {

    Expense saveExpense(Expense expense);
    List<Expense> getAllEmployees();
    List<Expense> findExpenseByUserCodeAndDate(Long userCode, String date) throws ParseException;
    String deleteExpense(String id);
    Expense updateExpense(Expense expense);
    Expense addCategoryAutomatically (Expense expense);
}
