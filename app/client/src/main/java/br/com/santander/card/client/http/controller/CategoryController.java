package br.com.santander.card.client.http.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@GetMapping(params="description")
	public ResponseEntity searchByDescription(final String descriprion) {
		return null;
	}
	
}
