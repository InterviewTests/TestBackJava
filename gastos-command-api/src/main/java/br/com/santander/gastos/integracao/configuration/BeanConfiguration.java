package br.com.santander.gastos.integracao.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    private static final String queueName = "filaGastos";

    @Bean(name="filaGastos")
    Queue queue() {
        return new Queue(queueName, false);
    }
}
