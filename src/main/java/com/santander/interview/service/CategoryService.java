package com.santander.interview.service;

import com.santander.interview.domain.Category;

import java.util.List;

public interface CategoryService {
    public void saveCategory(Category newCategory);
    public List<Category> searchCategoryByDetailSubstring(String detailSubstring);
}
