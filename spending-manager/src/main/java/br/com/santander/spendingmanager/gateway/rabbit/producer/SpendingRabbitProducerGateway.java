package br.com.santander.spendingmanager.gateway.rabbit.producer;

import br.com.santander.spendingmanager.config.RabbitConfig;
import br.com.santander.spendingmanager.domain.Spending;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpendingRabbitProducerGateway {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public SpendingRabbitProducerGateway(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Spending spending) {
        rabbitTemplate.convertAndSend(RabbitConfig.SPENDING_EXCHANGE, RabbitConfig.SPENDING_ROUTING_KEY, spending);
    }
}
