package br.com.santander.app.repository.redis;

import java.util.List;

import br.com.santander.app.model.Category;

public interface CategoryRepositoryRedis {

	Category insert(Category category);

	List<Category> findCategorySuggestionByDescription(String description);

	Category findByDescriptionEqualsIgnoreCase(String description);
}
