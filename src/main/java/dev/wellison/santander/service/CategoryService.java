package dev.wellison.santander.service;

import dev.wellison.santander.domain.Category;

import java.util.List;

public interface CategoryService {

    Category addCategory(Category request);
    List<Category> searchCategoryByDescription(String searchCategory);
}
