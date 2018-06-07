package br.com.santander.card.sale.http.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.card.sale.http.dto.CreateSaleRequest;

@RestController
@RequestMapping(path="sale")
public class SaleController {
	
	@PostMapping
	public void insert(CreateSaleRequest createSaleRequest) {
		
	}
	
}
