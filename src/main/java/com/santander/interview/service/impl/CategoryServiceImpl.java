package com.santander.interview.service.impl;

import com.santander.interview.domain.Category;
import com.santander.interview.repository.CategoryRepository;
import com.santander.interview.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    private String generateUuid() {  return UUID.randomUUID().toString(); }

    @Override
    public void saveCategory(Category category) {
        category.setId(generateUuid());
        categoryRepository.save(category);
    }
}
