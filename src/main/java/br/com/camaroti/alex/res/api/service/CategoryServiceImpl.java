package br.com.camaroti.alex.res.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.camaroti.alex.res.api.model.Category;
import br.com.camaroti.alex.res.api.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Override
	public Category findByName(String name) {
		return repository.findByNameIgnoreCase(name);
	}

	@Override
	public Category save(Category category) {
		return repository.save(category);
	}

}
