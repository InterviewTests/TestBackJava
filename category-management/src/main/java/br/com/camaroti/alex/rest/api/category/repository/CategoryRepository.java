package br.com.camaroti.alex.rest.api.category.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.camaroti.alex.rest.api.category.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

	Category findByNameIgnoreCase(String name);
	List<Category> findByNameContainingIgnoreCase(String name);	
}
