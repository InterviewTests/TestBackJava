package br.com.santander.card.client.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.santander.card.client.model.entity.Sale;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleServiceTest {
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private EntityManager entityManager;
	
	private static final Long USER_EXISTENT = 10l;
	private static final Long USER_NONEXISTENT = 100L;
	private Sale sale1 = null;
	private Sale sale2 = null;
	
	@Before
	public void start() {
		sale1 = new Sale();
		sale1.setCodigousuario(USER_EXISTENT);
		sale1.setCategoria("cat1");
		sale1.setDescricao("Desc1");
		sale1.setData(Date.from(Instant.now()));
		sale1.setValor(100);
		
		sale2 = new Sale();
		sale2.setCodigousuario(USER_EXISTENT);
		sale2.setCategoria("cat1");
		sale2.setDescricao("Desc1");
		sale2.setData(Date.from(Instant.now()));
		sale2.setValor(100);
		
		entityManager.persist(sale1);
		entityManager.persist(sale2);
	}
	
	@After
	public void end() {
		//clear database
	}
	
	@Test
	public void updateCategory() {
		
	}

	@Test
	public void findAllByUser_Existent() {
		List<Sale> sales = saleService.findAllByUser(USER_NONEXISTENT);
		assertEquals(2, sales.size());
	}
	
	@Test
	public void findAllByUser_Nonexistent() {
		List<Sale> sales = saleService.findAllByUser(USER_NONEXISTENT);
		assertTrue(sales.isEmpty());
	}

	@Test
	public void findById_NotFound() {
		Long saleId = 9999l;
		Sale sale = saleService.findById(saleId);
		assertNull(sale);
	}
	
	@Test
	public void findById_Found() {
		Long saleId = null;
		Sale sale = saleService.findById(saleId);
	}
}
