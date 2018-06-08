package br.com.santander.card.sale.listeners;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class AMQPListener implements Runnable {

	@Override
	public void run() {
		
	}

}
