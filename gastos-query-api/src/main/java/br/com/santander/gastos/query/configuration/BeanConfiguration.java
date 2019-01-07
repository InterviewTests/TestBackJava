package br.com.santander.gastos.query.configuration;

import br.com.santander.gastos.query.listener.FilaListenerImpl;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    private static final String filaGastos = "gastos";

    @Bean
    public Queue queue() {
        return new Queue(filaGastos, false);
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(filaGastos);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(FilaListenerImpl filaListener) {
        final MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(filaListener, "receiveMessage");
        return listenerAdapter;
    }
}
