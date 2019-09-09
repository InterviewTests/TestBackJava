package com.santander.interview.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

public class Expense {
    @Id
    private String id;
    private String description;
    private double value;
    private long userCode;
    private Date date;
    private Category category;

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

    @Override
    public String toString() {
        return String.format(
                "Expense[id=%s, descricao=%s, valor=%f, codigoUsuario=%d, data=%s]",
                this.id, this.description, this.value, this.userCode, this.date.toString()
        );
    }
}
