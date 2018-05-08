package br.com.santander.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.santander.app.converter.CategoryConverter;
import br.com.santander.app.dto.CategoryDTO;
import br.com.santander.app.exception.CategoryNotFoundException;
import br.com.santander.app.model.Category;
import br.com.santander.app.repository.CategoryRepository;
import br.com.santander.app.repository.redis.CategoryRepositoryRedis;

@Transactional(readOnly = true)
@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryRepositoryRedis categoryRepositoryRedis;

	@Override
	public List<CategoryDTO> findCategorySuggestionByDescription(final String description) {
		List<Category> listCategories = categoryRepositoryRedis.findCategorySuggestionByDescription(description);
		if(listCategories.isEmpty()) {
			listCategories = categoryRepository.findByDescriptionContainingIgnoreCase(description);
		}
		if(listCategories.isEmpty()) {
			throw new CategoryNotFoundException("Categories not found with this description: "+ description);
		}
		return CategoryConverter.toDTO(listCategories);
	}

	@Transactional(readOnly = false)
	@Override
	public Category categorizeExpenses(final String description) {
		Category category = categoryRepositoryRedis.findByDescriptionEqualsIgnoreCase(description);
		if(category == null) {
			category = categoryRepository.findByDescriptionEqualsIgnoreCase(description);
		}
		if (description != null && category == null) {
			category = new Category();
			category.setDescription(description);
			return categoryRepositoryRedis.insert(categoryRepository.save(category));
		}
		return category;
	}

}
