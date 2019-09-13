package com.santander.interview.domain;

import com.santander.interview.repository.CategoryRepository;
import com.santander.interview.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Component
public class Expense {
    @Id
    private String id;
    private String description;
    private double value;
    private long userCode;
    private Date date;
    private Category category;

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    CategoryRepository categoryRepository;

    private String generateID() { return UUID.randomUUID().toString(); }

    public Expense() { this.id = this.generateID(); }

    public Expense(String description, double value, long userCode, Date date) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.value = value;
        this.userCode = userCode;
        this.date = date;
    }

    public Expense(String description, double value, long userCode, Date date, Category category) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.value = value;
        this.userCode = userCode;
        this.date = date;
        this.category = category;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public double getValue() { return value; }

    public void setValue(double value) { this.value = value; }

    public long getUserCode() { return userCode; }

    public void setUserCode(long userCode) { this.userCode = userCode; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }

    private Category automaticCategorization(Expense expense) {
        Category category = expense.getCategory();
        if(category != null && category.getDetail() != null) {
            List<Category> list = categoryRepository.findByDetail(category.getDetail());
            if (list.size() > 0)
                return list.get(0);
        }
        return null;
    }

    public void add(Expense expense) {
        Expense newExpense = new Expense(
                expense.getDescription(),
                expense.getValue(),
                expense.getUserCode(),
                expense.getDate()
        );
        newExpense.setCategory(this.automaticCategorization(expense));
        this.expenseRepository.save(newExpense);
    }

    public List<Expense> searchByUserCode(long userCode) {
        List<Expense> expenses = expenseRepository.findByUserCode(userCode);
        Collections.sort(expenses, new Comparator<Expense>() {
            @Override
            public int compare(Expense expense1, Expense expense2) {
                return expense2.getDate().compareTo(expense1.getDate());
            }
        });
        return expenses;
    }

    public List<Expense> searchByUserCodeAndDate(long userCode, String date) throws ParseException {
        long oneDayInMilliseconds = 1000 * 60 * 60 * 24;

        Date startDate = new SimpleDateFormat("ddMMyyyy").parse(date);
        Date endDate = new Date(startDate.getTime() + oneDayInMilliseconds);
        return this.expenseRepository.findByUserCodeAndDateBetween(userCode, startDate, endDate);

    }

    public boolean update(String id, Expense expense) {
        Optional<Expense> existExpense = this.expenseRepository.findById(id);
        if(existExpense.isPresent()) {
            Expense expenseFound = existExpense.get();
            expense.setId(expenseFound.getId());
            expense.setCategory(this.automaticCategorization(expense));
            this.expenseRepository.save(expense);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format(
                "Expense[id=%s, descricao=%s, valor=%f, codigoUsuario=%d, data=%s]",
                this.id, this.description, this.value, this.userCode, this.date.toString()
        );
    }
}
