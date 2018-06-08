package br.com.santander.card.sale.listeners;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

import br.com.santander.card.sale.InsertSupplier;
import br.com.santander.card.sale.Sale;
import lombok.extern.java.Log;

@Log
@Component
public class AMQPListener {
	@Autowired
	private ApplicationContext applicationContext;
	
	private ExecutorService executor = null;
	
	@PostConstruct
	private void init() {
		this.executor = Executors.newFixedThreadPool(100);	
	}
	
	@RabbitListener(queues="queue.sales")
    public void recievedMessage(Sale sale, final Channel channel, final @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
		CompletableFuture.supplyAsync(
				(InsertSupplier)applicationContext.getBean(InsertSupplier.class, channel, tag, sale),
				executor
		);
    }
	
	@Bean("insertSupplier")
	private InsertSupplier insertSupplier(Channel channel, long tag, Sale sale) {
		return new InsertSupplier(channel, tag, sale);
	}

}
