package com.santander.interview.service.impl;

import com.santander.interview.domain.Category;
import com.santander.interview.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    Category category;

    @Override
    public void saveCategory(Category newCategory) {
        this.category.save(newCategory);
    }

    @Override
    public List<Category> searchCategoryByDetailSubstring(String detailSubstring) {
        return this.category.searchByDetailSubstring(detailSubstring);
    }
}
