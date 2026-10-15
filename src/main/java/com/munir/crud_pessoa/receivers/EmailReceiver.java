package com.munir.crud_pessoa.receivers;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.munir.crud_pessoa.dtos.response.EmailResponseDTO;
import com.munir.crud_pessoa.entidades.EmailDeliverLog;
import com.munir.crud_pessoa.services.EmailDeliverLogService;
import com.munir.crud_pessoa.services.EmailService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailReceiver implements Receiver<EmailResponseDTO> {
	
	private final EmailService emailService;
	
	private final EmailDeliverLogService logEnvioEmailService;
	
	private final ObjectMapper objectMapper;

	@Override
	@RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
	public void receber(EmailResponseDTO email) {
		
		try {
			
			emailService.send(email);
			
		} catch (MessagingException e) {
			
			try {
				
				EmailDeliverLog log = new EmailDeliverLog(null, objectMapper.writeValueAsString(email.addressees()),
														  email.subject(), email.content(), e.getMessage(), LocalDateTime.now());
				
				logEnvioEmailService.save(log);
				
			} catch (JsonProcessingException e1) {

				e1.printStackTrace();
			}
		}
	}

}