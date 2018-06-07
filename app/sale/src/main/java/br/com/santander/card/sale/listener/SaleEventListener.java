package br.com.santander.card.sale.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.santander.card.sale.event.CreateSaleEvent;

@Component
public class SaleEventListener {

	@EventListener
	@Async
	public void createSaleEventListener(CreateSaleEvent createSaleEvent) {
		
	}
	
}
