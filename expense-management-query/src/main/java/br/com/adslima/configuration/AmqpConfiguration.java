package br.com.adslima.configuration;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.amqp.eventhandling.DefaultAMQPMessageConverter;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.serialization.Serializer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author andrews.silva
 *
 */
@Slf4j
@Configuration
public class AmqpConfiguration {

	@Bean
	public SpringAMQPMessageSource complaintEventsMethod(Serializer serializer) {
		return new SpringAMQPMessageSource(new DefaultAMQPMessageConverter(serializer)) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.axonframework.amqp.eventhandling.spring.
			 * SpringAMQPMessageSource#onMessage(org.springframework.amqp.core.
			 * Message, com.rabbitmq.client.Channel)
			 */
			@RabbitListener(queues = "${axon.amqp.exchange}")
			@Override
			public void onMessage(Message message, Channel channel) {
				log.debug("Event Received: {}", message.getBody().toString());
				super.onMessage(message, channel);
			}
		};
	}
}