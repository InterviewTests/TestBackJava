package br.com.camaroti.alex.rest.api.category.service;

import java.io.IOException;
import java.util.List;

import br.com.camaroti.alex.rest.api.category.domain.Category;

public interface CategoryService {

	Category findByNameIgnoreCase(String name);
	List<Category> findByNameContaining(String name) throws IOException;
	Category save(Category category) throws IOException;
}
