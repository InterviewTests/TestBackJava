package com.santander.interview.domain;

import org.springframework.data.annotation.Id;

public class Category {
    @Id
    private String id;
    private String detail;

    public Category() { }

    public Category(String id, String detail) {
        this.id = id;
        this.detail = detail;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getDetail() { return detail; }

    public void setDetail(String detail) { this.detail = detail; }

}
