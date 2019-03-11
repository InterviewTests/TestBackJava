package br.com.camaroti.alex.res.api.service;

import br.com.camaroti.alex.res.api.model.Category;

public interface CategoryService {

	Category findByName(String name);
	Category save(Category category);
}
