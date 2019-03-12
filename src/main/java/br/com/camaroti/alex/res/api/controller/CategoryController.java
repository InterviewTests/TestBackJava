package br.com.camaroti.alex.res.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.camaroti.alex.res.api.model.Category;
import br.com.camaroti.alex.res.api.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(path="/categories/suggest")
	public @ResponseBody List<Category> suggestCategory(@RequestParam(value="name", required = false, defaultValue = "") String name) {
		return categoryService.findByNameContaining(name);
	}

}
