package br.com.santander.card.sale.data.commands;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.santander.card.sale.Sale;
import lombok.extern.java.Log;

@Log
@Component
@Scope("prototype")
public class InsertCommand {
	
	public boolean insert(Sale sale) {
		log.info(sale.toString());
		return true;
	}
	
}
