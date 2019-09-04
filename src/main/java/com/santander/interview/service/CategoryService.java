package com.santander.interview.service;

import com.santander.interview.domain.Category;

import java.util.List;

public interface CategoryService {
    public void saveCategory(Category category);
    public List<Category> searchCategoryByDetailPrefix(String detailPrefix);
}
