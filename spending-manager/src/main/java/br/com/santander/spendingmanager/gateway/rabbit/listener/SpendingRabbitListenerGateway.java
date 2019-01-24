package br.com.santander.spendingmanager.gateway.rabbit.listener;

import br.com.santander.spendingmanager.config.RabbitConfig;
import br.com.santander.spendingmanager.domain.Spending;
import br.com.santander.spendingmanager.gateway.SpendingGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpendingRabbitListenerGateway {

    private static final Logger log = LoggerFactory.getLogger(SpendingRabbitListenerGateway.class);
    private SpendingGateway spendingGateway;

    @Autowired
    public SpendingRabbitListenerGateway(SpendingGateway spendingGateway) {
        this.spendingGateway = spendingGateway;
    }

    @RabbitListener(queues = RabbitConfig.SPENDING_QUEUE)
    public void receiveSpendings(Spending spending) {
        log.info("Receiving spending: {}", spending);
        spendingGateway.saveOrUpdate(spending);
    }
}