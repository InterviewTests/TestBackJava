package br.com.santander.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.app.exception.ExpenseError;
import br.com.santander.app.exception.ExpenseExceptionHandler;
import br.com.santander.app.service.CategoryService;

@RequestMapping("/categories")
@CrossOrigin
@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/{description}", method = RequestMethod.GET , produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findCategorySuggestionByDescription(@PathVariable final String description){
		try {
			return new ResponseEntity<>(categoryService.findCategorySuggestionByDescription(description), HttpStatus.OK);
		} catch (final RuntimeException e) {
			return new ResponseEntity<>(new ExpenseError(1, ExpenseExceptionHandler.getExcetionError(e)), HttpStatus.EXPECTATION_FAILED);
		}
	}
}
