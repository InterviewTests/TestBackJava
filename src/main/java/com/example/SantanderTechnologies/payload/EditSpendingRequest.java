package com.example.SantanderTechnologies.payload;

public class EditSpendingRequest {
    private Long spendingId;
    private String category;

    public Long getSpendingId() {
        return spendingId;
    }

    public void setSpendingId(Long spendingId) {
        this.spendingId = spendingId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
