package com.munir.crud_pessoa.config;

import java.util.HashMap;
import java.util.Map;

import org.javers.spring.auditable.AuthorProvider;
import org.javers.spring.auditable.CommitPropertiesProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class JaversConfig {

	@Autowired
	HttpServletRequest request;
	
	@Bean
	CommitPropertiesProvider commitPropertiesProvider() {
	    return new CommitPropertiesProvider() {
	        @Override
	        public Map<String, String> provideForCommittedObject(Object savedDomainObject) {
	            Map<String, String> props = new HashMap<>();
	            props.put("endereco_ip", obterIpAtual());
	            return props;
	        }
	    };
	}
	
	@Bean
	AuthorProvider authorProvider() {
	    return () -> "SISTEMA";
	}
	
	private String obterIpAtual() {

	    return request.getRemoteAddr();
	}
}