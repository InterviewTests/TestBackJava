package com.example.SantanderTechnologies.payload;

import java.util.Date;

public class CreditCardRequest {
    private String number;

    private Integer validationCode;

    private String clientName;

    private Date duoDate;

    public CreditCardRequest(String number, Integer validationCode, String clientName, Date duoDate) {
        this.number = number;
        this.validationCode = validationCode;
        this.clientName = clientName;
        this.duoDate = duoDate;
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
}
