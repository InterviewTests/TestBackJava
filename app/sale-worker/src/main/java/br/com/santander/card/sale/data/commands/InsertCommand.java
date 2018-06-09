package br.com.santander.card.sale.data.commands;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.santander.card.sale.Sale;
import lombok.extern.java.Log;

@Log
@Component
@Scope("prototype")
public class InsertCommand {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Sale insert(Sale sale) {
		log.info(sale.toString());
		entityManager.persist(sale);
		return sale;
	}
	
}
