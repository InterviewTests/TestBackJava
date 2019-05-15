package com.example.SantanderTechnologies.payload;

import com.example.SantanderTechnologies.model.CreditCard;
import com.example.SantanderTechnologies.model.SpendingCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class SpendingRequest {

    private String location;

    private BigDecimal value;

    private String category;

    private Date date;

    private String creditCardNumber;

    public SpendingRequest(){}

    public SpendingRequest(BigDecimal value, String creditCardNumber) {
        this.value = value;
        this.creditCardNumber = creditCardNumber;
        this.date = new Date();
        this.location = "Default Location";
    }

    public SpendingRequest(String location, BigDecimal value, String category, Date date, String creditCardNumber) {
        this.location = location;
        this.value = value;
        this.category = category;
        this.date = date;
        this.creditCardNumber = creditCardNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
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
