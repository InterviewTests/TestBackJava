package br.com.santander.card.client.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.santander.card.client.data.jpa.SaleSQLCommand;
import br.com.santander.card.client.data.jpa.SaleSQLQuery;
import br.com.santander.card.client.model.entity.Sale;

@Service
public class SaleService {
	
	@Autowired
	private SaleSQLCommand saleSQLCommand;
	
	@Autowired
	private SaleSQLQuery saleSQLQuery;
	
	public Sale updateCategoryFromSale(Sale sale) {
		return saleSQLCommand.updateCategoryFromSale(sale);
	}

	public List<Sale> findAllByUser(Long userCode) {
		return saleSQLQuery.findAllByUserCode(userCode);
	}

	public List<Sale> findByUsarAndDate(Long userCode, Date date) {
		return saleSQLQuery.findAllByDate(userCode, date);
	}

	public List<Sale> findByUsarAndDates(Long userCode, Date dateStart, Date dateEnd) {
		return saleSQLQuery.findAllByDates(userCode, dateStart, dateEnd);
	}
}
