package br.com.santander.testbackjava.incluirgastocartao;

import static org.mockito.Mockito.spy;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.santander.testbackjava.exception.SistemaCredenciadoException;
import br.com.santander.testbackjava.facade.GastoCartaoFacade;
import br.com.santander.testbackjava.integration.kafka.producer.GastoCartaoProducer;
import br.com.santander.testbackjava.model.dto.GastoCartaoDTO;
import br.com.santander.testbackjava.service.GastoCartaoService;
import br.com.santander.testbackjava.service.SistemaCredenciadoService;
import br.com.santander.testbackjava.service.ValidateGestaoCartaoService;

public class IncluirGastoCartaoProducerTest {

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
	public void testIncluirGastoCartaoSendProducer() throws SistemaCredenciadoException {

		GastoCartaoDTO gastoCartaoDTO = new GastoCartaoDTO();
		gastoCartaoDTO.setDescricao("teste gasto");
		gastoCartaoDTO.setCodigoUsuario(1l);
		gastoCartaoDTO.setValor(BigDecimal.TEN);

		Mockito.doNothing().when(gastoCartaoProducer).sendIncluiGastoCartao(gastoCartaoDTO);
		
	}
}
