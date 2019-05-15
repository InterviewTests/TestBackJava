package com.example.SantanderTechnologies.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalAmount;

    private String number;

    private Integer validationCode;

    private String clientName;

    private Date duoDate;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    private User user;

    @OneToMany
    private List<Spending> spendings;

    public CreditCard(String clientName, String number, Integer validationCode, Date duoDate) {
        this.clientName = clientName;
        this.number = number;
        this.validationCode = validationCode;
        this.duoDate = duoDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(Integer validationCode) {
        this.validationCode = validationCode;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Date getDuoDate() {
        return duoDate;
    }

    public void setDuoDate(Date duoDate) {
        this.duoDate = duoDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Spending> getSpendings() {
        return spendings;
    }

    public void setSpendings(List<Spending> spendings) {
        this.spendings = spendings;
    }
}
