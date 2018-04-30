package br.com.santander.builder;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import br.com.santander.model.Gasto;
import br.com.santander.vo.CartaoVO;

public class CartaoModelToVOBuilderTest {
	private static final long CARTAO_UM = 4444555566664444444L;
	private static final long CARTAO_DOIS = 4444555566664444445L;

	@Test
	public void buildTest() {
		
		List<Gasto> gastos = new ArrayList<Gasto>();
		Gasto g1 = new Gasto(UUID.randomUUID(), CARTAO_UM, "Mercado", new BigDecimal("500.53"), 123354851L, Calendar.getInstance().getTime());
		Gasto g2 = new Gasto(UUID.randomUUID(), CARTAO_DOIS, "Shopping", new BigDecimal("1583.68"), 15565115L, Calendar.getInstance().getTime());
		Gasto g3 = new Gasto(UUID.randomUUID(), CARTAO_UM, "Shopping", new BigDecimal("1583.68"), 15565115L, Calendar.getInstance().getTime());
		Gasto g4 = new Gasto(UUID.randomUUID(), CARTAO_UM, "Shopping", new BigDecimal("1583.68"), 15565115L, Calendar.getInstance().getTime());
		Gasto g5 = new Gasto(UUID.randomUUID(), CARTAO_DOIS, "Shopping", new BigDecimal("1583.68"), 15565115L, Calendar.getInstance().getTime());
		
		gastos.add(g1);
		gastos.add(g2);
		gastos.add(g3);
		gastos.add(g4);
		gastos.add(g5);
		
		List<CartaoVO> cartoes = new CartaoModelToVOBuilder(gastos).build();
		
		assertEquals(2, cartoes.size());
		
		for (CartaoVO cartaoVO : cartoes) {
			
			if(cartaoVO.getNumeroCartao() == CARTAO_UM) {
				assertEquals(3, cartaoVO.getGastos().size());
			}
			
			if(cartaoVO.getNumeroCartao() == CARTAO_DOIS) {
				assertEquals(2, cartaoVO.getGastos().size());
			}
		}
	}
}
