package com.santander;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.santander.model.Gasto;
import com.santander.repository.GastoRepositoy;
import com.santander.repository.filter.GastoFilter;
import com.santander.service.GastoService;
import com.santander.service.IGastoService;

@RunWith(SpringRunner.class)
public class IntegracaoGastosTest {

	@MockBean
	private GastoRepositoy gastoRepository;

	private IGastoService gastoService;

	private Gasto gasto;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() {

		gasto = new Gasto();
		gasto.setDescricao("Primeiro Gasto");
		gasto.setValor(new BigDecimal(1.0));
		gasto.setCodigoUsuario(1);
		gasto.setData(LocalDateTime.now());
		gasto.setCategoria("teste");
		gastoService = new GastoService(gastoRepository);
	}

	@Test
	public void deveSalvarGastosNoRepositorio() {
		gastoService.salvar(gasto);
		verify(gastoRepository).save(gasto);
	}

	@Test
	public void deveSalvar2GastosNoRepositorio() throws Exception {

		Gasto gasto2 = new Gasto();
		gasto2.setCategoria(null);
		gasto2.setDescricao("Segundo Gsato");
		gasto2.setValor(new BigDecimal(10.00));
		gasto2.setCodigoUsuario(2);
		gasto2.setData(LocalDateTime.now());

		gastoService.salvar(gasto);

		gastoService.salvar(gasto2);
		verify(gastoRepository, times(2)).save(gasto);

	}

	@Test
	public void deveSalvarCemMilGastosPorSegundoNoRepositorio() {

		long inicio = System.currentTimeMillis();

		for (int i = 0; i <= 100000 - 1; i++) {
			gastoService.salvar(gasto);
		}

		long fim = System.currentTimeMillis();
		long tempo = fim - inicio;

		verify(gastoRepository, times(100000)).save(gasto);
		assertTrue(tempo <= 1000);

	}

	@Test
	public void deveTrazerGastoParaDia() throws Exception {
		GastoFilter filter = new GastoFilter();

		filter.setData(LocalDate.now());
		when(gastoRepository.filtrar(filter, gasto.getCodigoUsuario())).thenReturn(new ArrayList<Gasto>());
		gastoService.filtrar(filter, gasto.getCodigoUsuario());
		verify(gastoRepository).filtrar(filter, gasto.getCodigoUsuario());
	}

}
