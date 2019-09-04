package br.com.santander;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.santander.dtos.GastoDTO;
import br.com.santander.services.GastoService;

@Rollback(true)
@RunWith(SpringRunner.class)
@SpringBootTest
public class GastosTest extends AbstractTest{

	@Autowired
	private GastoService gastoService;
	
	@Test
	public void listar() {
		assertThat(gastoService.listar(1).size()).isGreaterThan(0);
	}
	
	@Test
	public void listarPorData() {
		assertThat(gastoService.listarPorData("01/09/2019", 1).size()).isGreaterThan(0);
	}
	
	@Test
	public void cadastrar() {
		GastoDTO gasto = new GastoDTO();
		
		gasto.setDescricao("Teste unitário");
		gasto.setValor(BigDecimal.valueOf(0.50));
		
		assertThat(gastoService.cadastrar(gasto, 1).getUuid()).isNotBlank();
	}
	
	@Test
	public void get() {
		assertThat(gastoService.getDTO("c403abf5-81e9-432c-8180-b6f460a91749", 1).getUuid()).isNotBlank();
	}
	
	@Test
	public void alterarCategoria() {
		assertThat(gastoService.alterarCategoria("alterando", "c403abf5-81e9-432c-8180-b6f460a91749", 1).getUuid()).isNotBlank();
	}
	
	@Test
	public void pesquisarCategorias() {
		assertThat(gastoService.pesquisarCategorias("e", 1).size()).isGreaterThan(1);
	}
}

















