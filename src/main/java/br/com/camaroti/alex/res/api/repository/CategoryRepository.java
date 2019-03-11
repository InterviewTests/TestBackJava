package br.com.camaroti.alex.res.api.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.camaroti.alex.res.api.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

	Category findByNameIgnoreCase(String name);
}
