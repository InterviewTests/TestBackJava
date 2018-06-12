package br.com.santander.card.sale.data.commands;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
	
	@Transactional
	public Sale insert(Sale sale) {
		log.info(sale.toString());
		try {
			entityManager.persist(sale);
		} catch (Exception e) {
			e.printStackTrace();
		}
		entityManager.flush();
		return sale;
	}
	
}
