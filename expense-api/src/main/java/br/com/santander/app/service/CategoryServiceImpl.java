package br.com.santander.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.santander.app.converter.CategoryConverter;
import br.com.santander.app.data.repository.CategoryRepository;
import br.com.santander.app.dto.CategoryDTO;
import br.com.santander.app.exception.CategoryNotFoundException;
import br.com.santander.app.model.Category;

@Transactional(readOnly = true)
@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<CategoryDTO> findCategorySuggestionByDescription(final String description) {
		final List<Category> categories = categoryRepository.findByDescriptionContainingIgnoreCase(description);
		if(categories.isEmpty()) {
			throw new CategoryNotFoundException("Categories not found with this description: "+ description);
		}
		return CategoryConverter.toDTO(categories);
	}

}
