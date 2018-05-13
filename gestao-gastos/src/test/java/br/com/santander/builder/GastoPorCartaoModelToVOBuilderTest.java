package br.com.santander.builder;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import br.com.santander.model.GastoPorCartao;
import br.com.santander.vo.GastoVO;

public class GastoPorCartaoModelToVOBuilderTest {
	private static final long CARTAO_UM = 4444555566664444444L;
	private static final long CARTAO_DOIS = 4444555566664444445L;

	@Test
	public void buildTest() {
		
		List<GastoPorCartao> gastos = new ArrayList<GastoPorCartao>();
		GastoPorCartao g1 = new GastoPorCartao(UUID.randomUUID(), CARTAO_UM, "LojaFisica", "Mercado", new BigDecimal("500.53"), 123354851L, Calendar.getInstance().getTime());
		GastoPorCartao g2 = new GastoPorCartao(UUID.randomUUID(), CARTAO_DOIS, "LojaFisica","Shopping", new BigDecimal("1583.68"), 15565115L, Calendar.getInstance().getTime());
		
		gastos.add(g1);
		gastos.add(g2);
		
		List<GastoVO> gastosVO = new GastoPorCartaoModelToVOBuilder(gastos).buildList();
		
		assertEquals(2, gastosVO.size());
	}
}
