package br.com.santander.card.client.http.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.card.client.http.dto.Category;
import br.com.santander.card.client.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping(params="q")
	public ResponseEntity searchByCategory(@RequestParam("q") final String category) {
		Set<Category> categories = categoryService.findAllByStartCategory(category);
		
		return ResponseEntity.ok(categories);
	}

	
}
