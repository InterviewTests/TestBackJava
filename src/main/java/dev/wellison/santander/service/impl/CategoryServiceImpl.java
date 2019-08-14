package dev.wellison.santander.service.impl;

import dev.wellison.santander.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.wellison.santander.repository.CategoryRepository;
import dev.wellison.santander.service.CategoryService;

import java.util.List;
import java.util.UUID;

@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category request) {
        String id = UUID.randomUUID().toString();
        Category category = new Category(id,request.getDescription());
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> searchCategoryByDescription(String searchCategory) {

        return categoryRepository.findByDescription(searchCategory);
    }
}
