package com.munir.crud_pessoa.receivers;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.munir.crud_pessoa.dtos.response.EmailResponseDTO;
import com.munir.crud_pessoa.entidades.LogEnvioEmail;
import com.munir.crud_pessoa.services.EmailService;
import com.munir.crud_pessoa.services.LogEnvioEmailService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailReceiver implements Receiver<EmailResponseDTO> {
	
	private final EmailService emailService;
	
	private final LogEnvioEmailService logEnvioEmailService;
	
	private final ObjectMapper objectMapper;

	@Override
	@RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
	public void receber(EmailResponseDTO email) {
		
		try {
			
			emailService.enviarEmail(email);
			
		} catch (MessagingException e) {
			
			try {
				
				LogEnvioEmail logEnvioEmail = new LogEnvioEmail(null, objectMapper.writeValueAsString(email.destinatarios()),
																email.assunto(), email.corpo(),
																e.getMessage(), LocalDateTime.now());
				
				logEnvioEmailService.save(logEnvioEmail);
				
			} catch (JsonProcessingException e1) {

				e1.printStackTrace();
			}
		}
	}

}
