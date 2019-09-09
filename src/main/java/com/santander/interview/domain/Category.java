package com.santander.interview.domain;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class Category {
    @Id
    private String id;
    private String detail;

    private String generateID() { return UUID.randomUUID().toString(); }

    public Category() { }

    public Category(String detail) {
        this.id = this.generateID();
        this.detail = detail;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getDetail() { return detail; }

    public void setDetail(String detail) { this.detail = detail; }

}
