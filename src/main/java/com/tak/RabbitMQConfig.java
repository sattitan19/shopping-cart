package com.tak;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tak.rest.CartQueueSubscriber;

@Configuration
public class RabbitMQConfig {

	@Value("${app.exchange.name}")
	private String appExchange;
	@Value("${app.queue.name}")
	private String appQueue;
	@Value("${app.routing.key}")
	private String appRoutingKey;
	
	@Bean
	Queue queue() {
		return new Queue(appQueue, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(appExchange);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("");
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(appQueue);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(CartQueueSubscriber receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}
	
}
