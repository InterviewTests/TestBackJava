/*
 * © 2020 Altran - Copyright - Todos os direitos reservados.
 */
package br.com.santander.testbackjava.integration.kafka.producer;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.santander.testbackjava.model.dto.GastoCartaoDTO;
import lombok.extern.log4j.Log4j2;

/**
 * Producer para adicionar mensagem de Integração de gastos por cartão no tópico da fila.
 *
 * @author Altran - jabes
 * @since 28 de jul de 2020 22:16:21
 * @version x.x
 */
@Component
@Log4j2
public class GastoCartaoProducer {
	
	/**
	 * Atributo testbackjavaTopic
	 */
	@Value("${testbackjava.topic}")
    private String testbackjavaTopic;
 
	/**
	 * Atributo kafkaTemplate
	 */
	@SuppressWarnings("rawtypes")
	@Autowired
	private KafkaTemplate kafkaTemplate;
 
    @SuppressWarnings("unchecked")
	public void sendIncluiGastoCartao(final @RequestBody GastoCartaoDTO gastoCartaoDTO) {
    	log.info("INICIO - Método: GastoCartaoProducer.sendIncluiGastoCartao");
    	
        final String mensageKey = UUID.randomUUID().toString();
        kafkaTemplate.send(testbackjavaTopic, mensageKey, gastoCartaoDTO);
        
        log.info("FIM - Método: GastoCartaoProducer.sendIncluiGastoCartao");
    }

}
