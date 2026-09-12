package com.munir.crud_pessoa.publishers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.munir.crud_pessoa.dtos.request.EmailRequestDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailPublisher implements Publisher<EmailRequestDTO> {
	
	@Value("${spring.rabbitmq.template.exchange}")
	public String exchangeName;
	
	@Value("${spring.rabbitmq.template.routing-key}")
	public String routingKey;
	
	private final RabbitTemplate rabbitTemplate;

    @Override
    public void publicar(EmailRequestDTO email) {
    	
        rabbitTemplate.convertAndSend(exchangeName, routingKey, email);
    }

}
