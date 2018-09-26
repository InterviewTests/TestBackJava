package br.com.rbs.testebackjava.consumer;

import br.com.rbs.testebackjava.domain.Spending;
import br.com.rbs.testebackjava.producer.SpendingProducer;
import br.com.rbs.testebackjava.repository.SpendingRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SpendingConsumer {

    @Autowired
    private SpendingRepository spendingRepository;

    @JmsListener(destination = SpendingProducer.SPENDING_QUEUE, concurrency = "5")
    public void receiveUserQueue(final String json) {
        final Spending spending = new Gson().fromJson(json, Spending.class);
        spendingRepository.save(spending);
    }
}
