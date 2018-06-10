package br.com.santander.card.client.data.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.santander.card.client.model.entity.Sale;

@Component
@Scope("prototype")
public class SaleSQLQuery {
	@Autowired
	private EntityManager entityManager;
	
	public List<Sale> findAllByUserCode(final Long usercode) {
		List<Sale> sales = entityManager.createQuery("SELECT s FROM Sale c WHERE c.codigousuario = :codigousuario", Sale.class)
				.setParameter("codigousuario", usercode)
				.getResultList();
		return sales;
	}
	
	public List<Sale> findAllByDate(final Long usercode, final Date date) {
		List<Sale> sales = entityManager.createQuery("SELECT s FROM Sale c WHERE c.codigousuario = :codigousuario AND c.date = :date", Sale.class)
				.setParameter("codigousuario", usercode)
				.setParameter("date", date)
				.getResultList();
		return sales;
	}
	
	public List<Sale> findAllByDates(final Long usercode, final Date dateStart, final Date dateEnd) {
		List<Sale> sales = entityManager.createQuery("SELECT s FROM Sale c WHERE c.codigousuario = :codigousuario AND c.date = :date", Sale.class)
				.setParameter("codigousuario", usercode)
				.setParameter("dateStart", dateStart)
				.setParameter("dateEnd", dateEnd)
				.getResultList();
		return sales;
	}
	
}
