package br.com.santander.card.sale;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.santander.card.sale.data.commands.InsertCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public class InsertSupplier implements Supplier<Long> {
	private final long tag;
	private final Sale sale;
	
	@Autowired
	private InsertCommand command;
	
	@Override
	public Long get() {
		command.insert(sale);
		return tag;
	}

}
