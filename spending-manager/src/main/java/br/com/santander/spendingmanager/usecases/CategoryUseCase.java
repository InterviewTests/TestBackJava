package br.com.santander.spendingmanager.usecases;

import br.com.santander.spendingmanager.domain.Category;
import br.com.santander.spendingmanager.gateway.CategoryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryUseCase {
    private CategoryGateway categoryGateway;

    @Autowired
    public CategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public void saveCategory(Category category) {
        if (categoryGateway.categoryExists(category)) {
            return;
        }

        categoryGateway.create(category);
    }

    public List<Category> findCategoryByTextPart(String textPart) {
        return categoryGateway.findCategoryByTextPart(textPart);
    }
}
