package br.com.santander.spendingmanager.gateway;

import br.com.santander.spendingmanager.domain.Category;
import br.com.santander.spendingmanager.gateway.cassandra.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CategoryGateway {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryGateway(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public boolean categoryExists(final Category category) {
        return categoryRepository.existsCategoryByCategory(category.getCategory());
    }

    public void create(final Category category) {
        category.setId(UUID.randomUUID());
        categoryRepository.save(category);
    }

    public List<Category> findCategoryByTextPart(String textPart) {
        return categoryRepository.findCategoriesByCategoryStartsWith(textPart);
    }
}
