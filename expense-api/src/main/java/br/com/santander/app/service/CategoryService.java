package br.com.santander.app.service;

import java.util.List;

import br.com.santander.app.dto.CategoryDTO;

public interface CategoryService {

	List<CategoryDTO> findCategorySuggestionByDescription(String description);
}
