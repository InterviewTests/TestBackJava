package br.com.santander.spendingmanager.usecases;

import br.com.santander.spendingmanager.domain.Spending;
import br.com.santander.spendingmanager.gateway.rabbit.producer.SpendingRabbitProducerGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpendingUseCase {

    private SpendingRabbitProducerGateway spendingRabbitProducerGateway;

    @Autowired
    public SpendingUseCase(SpendingRabbitProducerGateway spendingRabbitProducerGateway)
    {
        this.spendingRabbitProducerGateway = spendingRabbitProducerGateway;
    }

    public void saveSpending(Spending spending) {
        spendingRabbitProducerGateway.sendMessage(spending);
    }
}
