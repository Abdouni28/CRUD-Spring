package com.munir.crud_pessoa.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.munir.crud_pessoa.dtos.request.MailRequestDTO;
import com.munir.crud_pessoa.entidades.Pessoa;
import com.munir.crud_pessoa.utils.ResourcesLoader;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender mailSender;
	
	@Autowired
	ResourcesLoader resourcesLoader;

	private static final String UTF_8_ENCODING = "UTF-8";

    public void send(MailRequestDTO<?> requestDTO) {
    	
        try {
        	
        	MimeMessage message = mailSender.createMimeMessage();
        	
        	String corpoEmail = getCorpoEmail(requestDTO);
            
            MimeMessageHelper helper = new MimeMessageHelper(message, UTF_8_ENCODING);
            helper.setTo(requestDTO.getDestinatarios().toArray(new String[0]));
			helper.setSubject(requestDTO.getEmailENUM().getAssunto());
			helper.setText(corpoEmail, true);
	        
	        mailSender.send(message);
	        
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
        
    }
    
    private String getCorpoEmail(MailRequestDTO<?> requestDTO) {
    	
    	String corpoEmail = resourcesLoader.loadResourceAsString(requestDTO.getEmailENUM().getArquivoEmail());
    	
    	if(requestDTO.getEmailENUM().getPossuiParametros() != Boolean.TRUE)
			return corpoEmail;
		
		Map<String, String> parametros = getParametrosEmail(requestDTO);
		
		for (Map.Entry<String, String> parametro : parametros.entrySet()) {
			corpoEmail = corpoEmail.replace(parametro.getKey(), parametro.getValue());
		}
		
		return corpoEmail;
	}
    
    private Map<String, String> getParametrosEmail(MailRequestDTO<?> requestDTO) {
		
		Map<String, String> parametros = new HashMap<>();
		
		switch (requestDTO.getEmailENUM()) {		
			case NOVA_PESSOA_CADASTRADA:
				parametros = montarParametrosEmailNovaPessoaCadastrada(requestDTO);
				break;			
			default:
				break;
		}
		
		return parametros;
	}
    
	private Map<String, String> montarParametrosEmailNovaPessoaCadastrada(MailRequestDTO<?> requestDTO) {
		
		Pessoa pessoa = (Pessoa) requestDTO.getObjeto();
		
		Map<String, String> parametros = new HashMap<>();
		
		parametros.put(":nome", pessoa.getNome());
		
		return parametros;
	}
}
