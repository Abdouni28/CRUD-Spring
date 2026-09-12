package com.munir.crud_pessoa.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${spring.rabbitmq.template.exchange}")
	public String exchangeName;
	
	@Value("${spring.rabbitmq.template.routing-key}")
	public String routingKey;
	
	@Value("${spring.rabbitmq.template.default-receive-queue}")
	public String queue;
    
    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchangeName);
    }


    @Bean
    Queue queue() {
        return QueueBuilder.durable(queue).build();
    }


    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }
    
    @Bean
    Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}