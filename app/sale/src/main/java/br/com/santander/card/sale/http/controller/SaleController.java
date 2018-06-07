package br.com.santander.card.sale.http.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.card.sale.event.CreateSaleEvent;
import br.com.santander.card.sale.http.dto.CreateSaleRequest;
import br.com.santander.card.sale.http.dto.CreateSaleResponse;

@RestController
@RequestMapping(path="sale")
public class SaleController {
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@PostMapping
	public ResponseEntity<CreateSaleResponse> insert(@RequestBody @Valid CreateSaleRequest createSaleRequest) {
		CreateSaleEvent event = new CreateSaleEvent(createSaleRequest);
		eventPublisher.publishEvent(event);
		return ResponseEntity.ok(new CreateSaleResponse());
	}
	
}
