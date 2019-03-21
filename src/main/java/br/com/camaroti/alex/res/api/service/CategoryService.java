package br.com.camaroti.alex.res.api.service;

import java.io.IOException;
import java.util.List;

import br.com.camaroti.alex.res.api.domain.Category;

public interface CategoryService {

	Category findByName(String name);
	List<Category> findByNameContaining(String name) throws IOException;
	Category save(Category category) throws IOException;
}
