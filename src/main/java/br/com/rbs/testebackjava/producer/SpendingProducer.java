package br.com.rbs.testebackjava.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SpendingProducer {

    public static final String SPENDING_QUEUE = "spending.queue";

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void send(String json) {
        this.jmsMessagingTemplate.convertAndSend(SPENDING_QUEUE, json);
    }

}