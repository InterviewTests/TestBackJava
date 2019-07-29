package dev.wellison.santander.service.impl;

import dev.wellison.santander.domain.Category;
import dev.wellison.santander.domain.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.wellison.santander.repository.CategoryRepository;
import dev.wellison.santander.repository.ExpenseRepository;
import dev.wellison.santander.service.ExpenseService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service(value = "expenseService")
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    CategoryRepository categoryRepository;


    public Expense saveExpense(Expense expense) {
        String id = UUID.randomUUID().toString();
        Expense exp = new Expense(id, expense.getDescription(), expense.getValue(), expense.getUserCode(), expense.getDate(), expense.getCategory());
        expenseRepository.save(exp);
        return exp;
    }

    @Override
    public List<Expense> getAllEmployees() {
        Iterable<Expense> result = expenseRepository.findAll();
        List<Expense> employeesList = new ArrayList<Expense>();
        result.forEach(employeesList::add);
        return employeesList;
    }

    @Override
    public List<Expense> findExpenseByUserCodeAndDate(Long userCode, String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(date);

        return expenseRepository.findByUserCodeAndDate(userCode, d);
    }

    @Override
    public String deleteExpense(String id) {
        boolean result = expenseRepository.existsById(id);
        expenseRepository.deleteById(id);
        return "{ \"success\" : "+ (result ? "true" : "false") +" }";
    }

    @Override
    public Expense updateExpense(Expense expense) {
        return expenseRepository.findById(expense.getId()).map(recordedExpense -> {
            recordedExpense.setCategory(expense.getCategory());

            Expense updatedExpense = expenseRepository.save(recordedExpense);

            return updatedExpense;
        }).orElse(null);
    }

    @Override
    public Expense addCategoryAutomatically(Expense expense) {
        List<Category> categories = categoryRepository.findByDescription(expense.getCategory().getDescription());
        if (categories.size()>0){
            expense.setCategory(categories.get(0));
        }

        return expenseRepository.save(expense);
    }
}
