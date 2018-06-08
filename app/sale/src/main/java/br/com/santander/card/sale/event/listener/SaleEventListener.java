package br.com.santander.card.sale.event.listener;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.santander.card.sale.amqp.AMQBeans;
import br.com.santander.card.sale.event.CreateSaleEvent;
import lombok.extern.java.Log;

@Component
@Log
public class SaleEventListener {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private ObjectMapper mapper;
	
	@EventListener
	@Async
	public void createSaleEventListener(CreateSaleEvent createSaleEvent) {
		log.info("Enviando pro RabbitMQ");
		try {
			this.rabbitTemplate.convertAndSend(AMQBeans.topicExchangeName, AMQBeans.keyRouter, mapper.writeValueAsString(createSaleEvent.getSource()));
			log.info("Enviado pro RabbitMQ!!!!!");
			log.info(mapper.writeValueAsString(createSaleEvent.getSource()));
		} catch (AmqpException e) {
			log.warning(e.getMessage());
		} catch (JsonProcessingException e) {
			log.warning(e.getMessage());
		}
	}
	
}
