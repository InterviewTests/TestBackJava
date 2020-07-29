package br.com.santander.testbackjava.incluirgastocartao;

import static org.mockito.Mockito.spy;

import java.math.BigDecimal;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.santander.testbackjava.facade.GastoCartaoFacade;
import br.com.santander.testbackjava.integration.kafka.consumer.GastoCartaoConsumer;
import br.com.santander.testbackjava.integration.kafka.producer.GastoCartaoProducer;
import br.com.santander.testbackjava.model.dto.GastoCartaoDTO;
import br.com.santander.testbackjava.service.GastoCartaoService;
import br.com.santander.testbackjava.service.SistemaCredenciadoService;
import br.com.santander.testbackjava.service.ValidateGestaoCartaoService;

public class IncluirGastoCartaoConsumerTest {

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

	@InjectMocks
	private GastoCartaoConsumer gastoCartaoConsumer = spy(GastoCartaoConsumer.class);

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testIncluirGastoCartaoSendConsumer() throws Exception {

		GastoCartaoDTO gastoCartaoDTO = new GastoCartaoDTO();
		gastoCartaoDTO.setDescricao("teste gasto");
		gastoCartaoDTO.setCodigoUsuario(1l);
		gastoCartaoDTO.setValor(BigDecimal.TEN);

		final String key = UUID.randomUUID().toString();

		String topic = "testbackjava-topic";

		int partition = 1;
		long offset = 10;

		ConsumerRecord<String, GastoCartaoDTO> consumerRecord = new ConsumerRecord<String, GastoCartaoDTO>(topic, partition,
				offset, key, gastoCartaoDTO);
		
		Mockito.doNothing().when(gastoCartaoConsumer).consumerIncluirGastoCartao(consumerRecord);

	}
}
