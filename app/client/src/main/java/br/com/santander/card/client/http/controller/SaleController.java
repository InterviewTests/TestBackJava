package br.com.santander.card.client.http.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.card.client.model.entity.Sale;
import br.com.santander.card.client.service.CategoryService;
import br.com.santander.card.client.service.SaleService;

@RestController
@RequestMapping("/{user}/sale")
public class SaleController {
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/")
	public ResponseEntity all(@PathVariable("user") final Long userCode) {
		List<Sale> sales = saleService.findAllByUser(userCode);
		return ResponseEntity.ok(sales);
	}
	
	@RequestMapping(path="", params= {"datestart", "dateend"})
	public ResponseEntity searchByDates(@PathVariable("user") final Long userCode) {
		Date dateStart = null;
		Date dateEnd = null;
		List<Sale> sales = saleService.findByUsarAndDates(userCode, dateStart, dateEnd);
		return ResponseEntity.ok(sales);
	}
	
	@PutMapping("/{saleId}/{category}")
	public ResponseEntity assingCategory(@PathVariable("user") final Long userCode, @PathVariable("saleId") final Long saleId, @PathVariable("category") final String category) {
		Sale sale = saleService.findById(saleId);
		sale.setCategoria(category);
		saleService.updateCategoryFromSale(sale);
		return ResponseEntity.ok(sale);
	}
	
}
