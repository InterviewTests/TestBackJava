/*
 * © 2020 Altran - Copyright - Todos os direitos reservados.
 */
package br.com.santander.testbackjava.integration.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.santander.testbackjava.model.dto.GastoCartaoDTO;
import br.com.santander.testbackjava.service.GastoCartaoService;
import lombok.extern.log4j.Log4j2;

/**
 * Consumer para receber mensagem de Integração de gastos por cartão.
 *
 * @author Altran - jabes
 * @since 28 de jul de 2020 22:28:31
 * @version x.x
 */
@Component
@Log4j2
public class GastoCartaoConsumer {
	
	/**
	 * Atributo gastoCartaoService
	 */
	@Autowired
	private GastoCartaoService gastoCartaoService;
	
	@KafkaListener(topics = "${testbackjava.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumerIncluirGastoCartao(final ConsumerRecord<String, GastoCartaoDTO> consumerRecord) throws Exception {
		log.info("INICIO - Método: GastoCartaoConsumer.consumerIncluirGastoCartao");
		
		log.info("key: " + consumerRecord.key());
		
        gastoCartaoService.incluirGastoCartao(consumerRecord.value());
        
        log.info("FIM - Método: GastoCartaoConsumer.consumerIncluirGastoCartao");
    }

}
