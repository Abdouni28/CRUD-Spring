package com.munir.crud_pessoa.services;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.munir.crud_pessoa.dtos.request.EmailRequestDTO;
import com.munir.crud_pessoa.dtos.response.EmailResponseDTO;
import com.munir.crud_pessoa.email_templates.EmailTemplate;
import com.munir.crud_pessoa.publishers.EmailPublisher;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
	
	private final EmailPublisher emailPublisher;

	private final JavaMailSender mailSender;
  
	private static final String UTF_8_ENCODING = "UTF-8";	 
	
	public void send(EmailResponseDTO response) throws MessagingException {
			
		MimeMessage message = mailSender.createMimeMessage();
  
	    MimeMessageHelper helper = new MimeMessageHelper(message, UTF_8_ENCODING);
	    helper.setTo(response.addressees().toArray(new String[0]));
	    helper.setSubject(response.subject());
	    helper.setText(response.content(), true);
	  
	    mailSender.send(message);
	}

    public void sendToQueue(EmailTemplate email) {
        	
    	EmailRequestDTO request = email.buildEmailTemplate();
    	
    	emailPublisher.publish(request);
    }
}