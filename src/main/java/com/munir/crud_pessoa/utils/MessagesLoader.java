package com.munir.crud_pessoa.utils;
  
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@PropertySource("messages.properties")
public class MessagesLoader {
	
	private final Environment env;

    public String loadMessage(String key) {
        	
    	return env.getProperty(key);
    }
}