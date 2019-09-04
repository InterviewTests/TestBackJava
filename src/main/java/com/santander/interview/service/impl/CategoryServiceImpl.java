package com.santander.interview.service.impl;

import com.santander.interview.domain.Category;
import com.santander.interview.repository.CategoryRepository;
import com.santander.interview.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    private String generateUuid() {  return UUID.randomUUID().toString(); }

    @Override
    public void saveCategory(Category category) {
        category.setId(generateUuid());
        this.categoryRepository.save(category);
    }

    @Override
    public List<Category> searchCategoryByDetailPrefix(String detailPrefix) {
        return this.categoryRepository.findByDetailLike(detailPrefix);
    }
}
