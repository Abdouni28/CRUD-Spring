package com.munir.crud_pessoa.services;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.munir.crud_pessoa.dtos.request.EmailRequestDTO;
import com.munir.crud_pessoa.emails.Email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender mailSender;

	private static final String UTF_8_ENCODING = "UTF-8";

    public void send(Email email) {
    	
        try {
        	
        	EmailRequestDTO requestDTO = email.montarEmailRequestDTO();
        	
        	MimeMessage message = mailSender.createMimeMessage();
            
            MimeMessageHelper helper = new MimeMessageHelper(message, UTF_8_ENCODING);
            helper.setTo(requestDTO.getDestinatarios().toArray(new String[0]));
			helper.setSubject(requestDTO.getAssunto());
			helper.setText(requestDTO.getCorpo(), true);
	        
	        mailSender.send(message);
	        
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
        
    }
}