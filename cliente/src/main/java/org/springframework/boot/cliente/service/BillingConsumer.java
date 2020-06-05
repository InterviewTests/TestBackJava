package org.springframework.boot.cliente.service;



import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BillingConsumer {

    @Autowired
    BillingService billingService;

    @KafkaListener(topics = "${order.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(ConsumerRecord consumerRecord) {
        log.info("key: " + consumerRecord.key());
        log.info("Headers: " + consumerRecord.headers());
        log.info("Partion: " + consumerRecord.partition());
        log.info("Order: " + consumerRecord.value());

        billingService.saveOrder(consumerRecord.value());

    }
}
