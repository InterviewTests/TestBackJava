package br.com.santander.card.sale.listeners;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

import br.com.santander.card.sale.CategoryService;
import br.com.santander.card.sale.InsertSupplier;
import br.com.santander.card.sale.Sale;
import lombok.extern.java.Log;

@Log
@Component
public class AMQPListener {
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private ApplicationContext context;
	
	private ExecutorService executor = null;
	
	@Autowired
	private CategoryService categoryService;
	
	@PostConstruct
	private void init() {
		 TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
		this.executor = Executors.newFixedThreadPool(100);

	}
	
	@RabbitListener(queues="queue.sales")
    public void recievedMessage(final String json, final Channel channel, final @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
		log.info("# received: "+json);
		log.info("#Channel@recievedMessage: "+channel);
		Sale sale = null;
		try {
			
			sale = mapper.readValue(json, Sale.class);
			
			String category = categoryService.findFirstByDescription(sale.getDescricao());
			sale.setCategoria(category);
			
			CompletableFuture<Long> cf = CompletableFuture.supplyAsync(
				(InsertSupplier)context.getBean("insertSupplier", tag, sale),
				executor
			);
			cf.thenAccept(t->{
				log.info("#Channel@thenAccept: "+channel);
			});
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
	
	@Bean("insertSupplier")
	@Scope("prototype")
	private InsertSupplier insertSupplier(long tag, Sale sale) {
		return new InsertSupplier(tag, sale);
	}

}
