package br.com.santander.card.sale.event;

import org.springframework.context.ApplicationEvent;

import br.com.santander.card.sale.http.dto.CreateSaleRequest;

public class CreateSaleEvent extends ApplicationEvent {

	public CreateSaleEvent(CreateSaleRequest createSaleRequest) {
		super(createSaleRequest);
	}

}
