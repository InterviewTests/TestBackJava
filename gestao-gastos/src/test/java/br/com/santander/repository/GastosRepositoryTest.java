package br.com.santander.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.santander.model.Gasto;
import br.com.santander.utils.DateUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GastosRepositoryTest {

	@Autowired
	private GastosRepository gastosRepository;
	
	@Before
	public void setUp() throws Exception {}

	private static final Long NUMERO_CARTAO = 4444555566664444444L;
	private static final String DESCRICAO = "Mercado";
	private static final BigDecimal VALOR = new BigDecimal("500.53");
	private static final long CODIGO_USUARIO = 123354851L;
	private static final Date DATA = Calendar.getInstance().getTime();
	
	/**
	 * Teste de persistencia basico
	 */
	@Test
	public void testPercistence() {
		
		Gasto g1 = new Gasto(UUID.randomUUID(), NUMERO_CARTAO, DESCRICAO, VALOR, CODIGO_USUARIO, DATA);
		
		gastosRepository.save(g1);
		
		assertNotNull(g1.getCodigoGasto());
		
		Gasto novoGasto = gastosRepository.findById(g1.getCodigoGasto()).orElse(null);
		
		assertEquals(NUMERO_CARTAO, novoGasto.getNumeroCartao());
		assertEquals(DESCRICAO, novoGasto.getDescricao());
		assertEquals(VALOR, novoGasto.getValor());
		assertEquals(DATA, novoGasto.getData());
		
		gastosRepository.delete(novoGasto);
		novoGasto = gastosRepository.findById(g1.getCodigoGasto()).orElse(null);
		
		assertNull(novoGasto);
	}
	
	@Test
	public void testConsultaData() {
		
		Gasto g1 = new Gasto(UUID.randomUUID(), NUMERO_CARTAO, DESCRICAO, VALOR, CODIGO_USUARIO, DATA);
		
		gastosRepository.save(g1);
		
		assertNotNull(g1.getCodigoGasto());
		
		List<Gasto> findByData = gastosRepository.findByData(DateUtils.getDataInicio(DATA), DateUtils.getDataFim(DATA));
		
		assertEquals(false, findByData.isEmpty());
		
		//Exclui registro utilizado para teste
		
		Gasto gastoExcluir = gastosRepository.findById(g1.getCodigoGasto()).orElse(null);
		
		gastosRepository.delete(gastoExcluir);
		
		gastoExcluir = gastosRepository.findById(g1.getCodigoGasto()).orElse(null);
		
		assertNull(gastoExcluir);
	}
	
	@Test
	public void testConsultaNumeroCartao() {
		
		Gasto g1 = new Gasto(UUID.randomUUID(), NUMERO_CARTAO, DESCRICAO, VALOR, CODIGO_USUARIO, DATA);
		
		gastosRepository.save(g1);
		
		assertNotNull(g1.getCodigoGasto());
		
		List<Gasto> findByNumeroCartao = gastosRepository.findByNumeroCartao(g1.getNumeroCartao());
		
		assertEquals(false, findByNumeroCartao.isEmpty());
		
		//Exclui registro utilizado para teste
		
		Gasto gastoExcluir = gastosRepository.findById(g1.getCodigoGasto()).orElse(null);
		
		gastosRepository.delete(gastoExcluir);
		
		gastoExcluir = gastosRepository.findById(g1.getCodigoGasto()).orElse(null);
		
		assertNull(gastoExcluir);
	}
}
