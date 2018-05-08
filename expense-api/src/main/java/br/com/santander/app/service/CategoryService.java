package br.com.santander.app.service;

import java.util.List;

import br.com.santander.app.dto.CategoryDTO;
import br.com.santander.app.model.Category;

public interface CategoryService {

	List<CategoryDTO> findCategorySuggestionByDescription(String description);

	Category categorizeExpenses(String description);
}
