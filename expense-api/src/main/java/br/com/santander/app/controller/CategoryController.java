package br.com.santander.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.app.service.CategoryService;

@RequestMapping("/santander/api/v1/categories")
@CrossOrigin
@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/{description}")
	public ResponseEntity<?> findCategorySuggestionByDescription(@PathVariable final String description){
		return new ResponseEntity<>(categoryService.findCategorySuggestionByDescription(description), HttpStatus.OK);
	}
}
