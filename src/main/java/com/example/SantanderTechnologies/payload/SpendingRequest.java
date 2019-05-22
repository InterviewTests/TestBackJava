package com.example.SantanderTechnologies.payload;

import java.math.BigDecimal;
import java.util.Date;

public class SpendingRequest {

    private String description;
    private BigDecimal amount;
    private String category;
    private Date date;

    private String creditCardNumber;

    public SpendingRequest(){}

    public SpendingRequest(BigDecimal amount, String creditCardNumber) {
        this.amount = amount;
        this.creditCardNumber = creditCardNumber;
        this.date = new Date();
        this.description = "Default Description";
    }

    public SpendingRequest(String description, BigDecimal amount, String category, Date date, String creditCardNumber) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.creditCardNumber = creditCardNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
}
