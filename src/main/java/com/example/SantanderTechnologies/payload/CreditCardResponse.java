package com.example.SantanderTechnologies.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.math.BigDecimal;
import java.util.Date;

public class CreditCardResponse extends ApiResponse{

    @JsonProperty("descricao")
    private String description;

    @JsonProperty("valor")
    @JsonFormat(shape=JsonFormat.Shape.STRING)
    private BigDecimal amount;

    @JsonProperty("codigousuario")
    private Long userCode;

    @JsonProperty("data")
    private Date date;

    public CreditCardResponse(Boolean success, String message) {
        super(success, message);
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

    public Long getUserCode() {
        return userCode;
    }

    public void setUserCode(Long userCode) {
        this.userCode = userCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
