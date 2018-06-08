package br.com.santander.card.sale;

import java.io.IOException;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

import br.com.santander.card.sale.data.commands.InsertCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
public class InsertSupplier implements Supplier<Object> {

	private final Channel channel;
	private final long tag;
	private final Sale sale;
	
	@Autowired
	private InsertCommand command;
	
	public InsertSupplier(Channel channel, long tag, Sale sale) {
		this.channel = channel;
		this.tag = tag;
		this.sale = sale;
	}
	
	@Override
	public Object get() {
		try {
			insertAndSendAck();
		} catch (Exception e) {
			
		}
		return null;
	}
	
	private void insertAndSendAck() throws IOException {
		if(command.insert(sale)) {
			channel.basicAck(tag, false);
			
		} else {
			channel.basicReject(tag, true);
			
		}
	}

}
