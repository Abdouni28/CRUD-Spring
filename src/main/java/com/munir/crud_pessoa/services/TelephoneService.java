package com.munir.crud_pessoa.services;

import java.text.MessageFormat;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.munir.crud_pessoa.dtos.request.TelephoneRequestDTO;
import com.munir.crud_pessoa.dtos.response.TelephoneResponseDTO;
import com.munir.crud_pessoa.entidades.Telephone;
import com.munir.crud_pessoa.mapper.TelephoneMapper;
import com.munir.crud_pessoa.repositories.TelephoneRepository;
import com.munir.crud_pessoa.utils.MessagesLoader;
import com.munir.crud_pessoa.validadores.TelephoneValidator;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TelephoneService {
	
	private final TelephoneMapper mapper;

	private final TelephoneValidator validator;

	private final TelephoneRepository repository;
	  
	public TelephoneResponseDTO insert(TelephoneRequestDTO request) {
		
		if(request.personId() == null)
			throw new IllegalArgumentException(MessageFormat.format(MessagesLoader.loadMessage("message.atributo_obrigatorio"),
																								"id_pessoa"));
	
		Telephone telephone = mapper.toEntity(request);
		
		validator.validate(telephone);
		
		repository.save(telephone);
		
		TelephoneResponseDTO response = mapper.toResponseDTO(telephone);	
	
		return response;
	}
	
	public TelephoneResponseDTO update(TelephoneRequestDTO request) {
    	
    	Optional<Telephone> optionalTelephone = repository.findById(request.id());
    	
    	if(optionalTelephone.isEmpty()) 
			throw new IllegalArgumentException(MessageFormat.format(MessagesLoader.loadMessage("message.nenhum_endereco_encontrado_by_id"),
											   request.id()));				
    	
    	Telephone telephone = optionalTelephone.get();
    	
    	mapper.toEntityUpdate(request, telephone);
    	
    	telephone = repository.save(telephone);
    	
    	TelephoneResponseDTO response = mapper.toResponseDTO(telephone);
  
    	return response;
    }
}