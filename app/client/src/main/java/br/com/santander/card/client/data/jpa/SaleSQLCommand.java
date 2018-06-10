package br.com.santander.card.client.data.jpa;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.santander.card.client.model.entity.Sale;

@Component
@Scope("prototype")
public class SaleSQLCommand {
	@Autowired
	private EntityManager entityManager;
	
	public Sale updateCategoryFromSale(Sale sale) {
		entityManager.persist(sale);
		return sale;
	}
	
}
