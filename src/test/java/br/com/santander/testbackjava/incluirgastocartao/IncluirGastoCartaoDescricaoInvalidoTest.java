package br.com.santander.testbackjava.incluirgastocartao;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.spy;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import br.com.santander.testbackjava.exception.GastoCartaoException;
import br.com.santander.testbackjava.facade.GastoCartaoFacade;
import br.com.santander.testbackjava.integration.kafka.producer.GastoCartaoProducer;
import br.com.santander.testbackjava.model.dto.GastoCartaoDTO;
import br.com.santander.testbackjava.service.GastoCartaoService;
import br.com.santander.testbackjava.service.SistemaCredenciadoService;
import br.com.santander.testbackjava.service.ValidateGestaoCartaoService;

public class IncluirGastoCartaoDescricaoInvalidoTest {

	@InjectMocks
	private GastoCartaoService gastoCartaoService = spy(GastoCartaoService.class);

	@InjectMocks
	private GastoCartaoFacade gastoCartaoFacade = spy(GastoCartaoFacade.class);

	@InjectMocks
	private SistemaCredenciadoService sistemaCredenciadoService = spy(SistemaCredenciadoService.class);

	@InjectMocks
	private ValidateGestaoCartaoService validateGestaoCartaoService = spy(ValidateGestaoCartaoService.class);

	@InjectMocks
	private GastoCartaoProducer gastoCartaoProducer = spy(GastoCartaoProducer.class);

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@Ignore
	public void testIncluirGastoCartaoSemDescricao() {

		GastoCartaoDTO gastoCartaoDTO = new GastoCartaoDTO();

		assertThatThrownBy(() -> gastoCartaoFacade.incluirGastoCartao(gastoCartaoDTO)).isInstanceOf(GastoCartaoException.class);

	}
}
