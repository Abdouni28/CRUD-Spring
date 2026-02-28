package com.munir.crud_pessoa.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ResourcesLoader {

	private final ResourceLoader resourceLoader;

    public String loadResourceAsString(String path) {
    	
        try {
        	
        	Resource resource = resourceLoader.getResource("classpath:" + path);
        	
			return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;
    }
}
