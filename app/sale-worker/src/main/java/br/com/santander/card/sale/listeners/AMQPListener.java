package br.com.santander.card.sale.listeners;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

import br.com.santander.card.sale.InsertSupplier;
import br.com.santander.card.sale.Sale;
import lombok.extern.java.Log;

@Log
@Component
public class AMQPListener {
	@Autowired
	private ObjectMapper mapper;
	
	private ExecutorService executor = null;
	
	@PostConstruct
	private void init() {
		this.executor = Executors.newFixedThreadPool(100);	
	}
	
	@RabbitListener(queues="queue.sales")
    public void recievedMessage(final String json, final Channel channel, final @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
		log.info("# received: "+json);
		Sale sale;
		try {
			sale = mapper.readValue(json, Sale.class);
			CompletableFuture.supplyAsync(
					new InsertSupplier(channel, tag, sale),
					executor
			);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
    }

}
