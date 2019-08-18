package com.santander;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.santander.model.Gasto;
import com.santander.repository.GastoRepositoy;
import com.santander.service.GastoService;
import com.santander.service.IGastoService;

@RunWith(SpringRunner.class)
public class IntegracaoGastosTest {

	@MockBean
	private GastoRepositoy gastoRepository;

	private IGastoService gastoService;

	private Gasto gasto;
	private List<Gasto> listGasto;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() {
		listGasto = new ArrayList<Gasto>();

		gasto = new Gasto();
		gasto.setDescricao("Primeiro Gsato");
		gasto.setValor(1.0);
		gasto.setCodigoUsuario(1);
		gasto.setData(LocalDateTime.now());

		gastoService = new GastoService(gastoRepository);
	}

	@Test
	public void deveSalvarGastosNoRepositorio() {

		listGasto.add(gasto);

		gastoService.salvar(listGasto);
		verify(gastoRepository).save(gasto);
	}

	@Test
	public void deveSalvar2GastosNoRepositorio() throws Exception {

		listGasto.add(gasto);
		listGasto.add(gasto);
		gastoService.salvar(listGasto);

		verify(gastoRepository, times(2)).save(gasto);

	}

	@Test
	public void deveSalvarCemMilGastosPorSegundoNoRepositorio() {

		long inicio = System.currentTimeMillis();

		for (int i = 0; i <= 100000 - 1; i++) {
			listGasto.add(gasto);
		}

		long fim = System.currentTimeMillis();
		long tempo = fim - inicio;

		gastoService.salvar(listGasto);
		verify(gastoRepository, times(100000)).save(gasto);

		assertTrue(tempo <= 1000);

	}

}
