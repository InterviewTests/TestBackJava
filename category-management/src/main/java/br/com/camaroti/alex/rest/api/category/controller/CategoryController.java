package br.com.camaroti.alex.rest.api.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.camaroti.alex.rest.api.category.domain.Category;
import br.com.camaroti.alex.rest.api.category.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(path="/categories/suggest")
	public @ResponseBody List<Category> suggestCategory(@RequestParam(value="name", required = false, defaultValue = "") String name) throws Exception {
		return categoryService.findByNameContaining(name);
	}
	
	@GetMapping(path="/category/{name}")
	public @ResponseBody Category findByNameIgnoreCase(@PathVariable(value="name", required = true) String name) throws Exception {
		return categoryService.findByNameIgnoreCase(name);
	}
	
	@PostMapping(path="/categories")
	public @ResponseBody Category addCategory(@RequestParam(value="name") String name) throws Exception {
		Category category = new Category();
		category.setName(name);
		return categoryService.save(category);
	}

}
