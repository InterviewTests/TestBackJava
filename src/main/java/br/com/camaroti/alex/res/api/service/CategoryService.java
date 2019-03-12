package br.com.camaroti.alex.res.api.service;

import java.util.List;

import br.com.camaroti.alex.res.api.model.Category;

public interface CategoryService {

	Category findByName(String name);
	List<Category> findByNameContaining(String name);
	Category save(Category category);
}
