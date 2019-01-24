package br.com.santander.spendingmanager.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String SPENDING_QUEUE = "spending_queue";
    public static final String SPENDING_EXCHANGE = "spending_exchange";
    public static final String SPENDING_ROUTING_KEY = "spending_routing_key";

    @Bean
    public TopicExchange appSpendingExchange() {
        return new TopicExchange(SPENDING_EXCHANGE);
    }

    @Bean
    public Queue appSpendingQueue() {
        return new Queue(SPENDING_QUEUE);
    }

    @Bean
    public Binding declareSpendingBinding() {
        return BindingBuilder.bind(appSpendingQueue()).to(appSpendingExchange()).with(SPENDING_ROUTING_KEY);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
}