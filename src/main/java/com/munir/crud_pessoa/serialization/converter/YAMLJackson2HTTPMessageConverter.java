package com.munir.crud_pessoa.serialization.converter;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public final class YAMLJackson2HTTPMessageConverter extends AbstractJackson2HttpMessageConverter {

	protected YAMLJackson2HTTPMessageConverter() {
		super(new YAMLMapper()
				  .setSerializationInclusion(JsonInclude.Include.NON_NULL), MediaType.parseMediaType("application/yaml"));
		// TODO Auto-generated constructor stub
	}


}
