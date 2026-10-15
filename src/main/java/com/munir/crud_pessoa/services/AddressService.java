package com.munir.crud_pessoa.services;

import java.text.MessageFormat;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.munir.crud_pessoa.dtos.request.AddressRequestDTO;
import com.munir.crud_pessoa.dtos.response.AddressResponseDTO;
import com.munir.crud_pessoa.entidades.Address;
import com.munir.crud_pessoa.mapper.AddressMapper;
import com.munir.crud_pessoa.repositories.AddressRepository;
import com.munir.crud_pessoa.utils.MessagesLoader;
import com.munir.crud_pessoa.validadores.AddressValidator;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressService {
	
	private final AddressMapper mapper;

	private final AddressValidator validator;

	private final AddressRepository repository;
	  
	public AddressResponseDTO insert(AddressRequestDTO request) {
		
		if(request.personId() == null)
			throw new IllegalArgumentException(MessageFormat.format(MessagesLoader.loadMessage("message.atributo_obrigatorio"),
																								"id_pessoa"));
	
		Address address = mapper.toEntity(request);
		
		validator.validate(address);
		
		repository.save(address);
		
		AddressResponseDTO response = mapper.toResponseDTO(address);	
	
		return response;
	}
	
	public AddressResponseDTO update(AddressRequestDTO request) {
    	
    	Optional<Address> optionalEndereco = repository.findById(request.id());
    	
    	if(optionalEndereco.isEmpty()) 
			throw new IllegalArgumentException(MessageFormat.format(MessagesLoader.loadMessage("message.nenhum_endereco_encontrado_by_id"),
											   request.id()));				
    	
    	Address address = optionalEndereco.get();
    	
    	mapper.toEntityUpdate(request, address);
    	
    	address = repository.save(address);
    	
    	AddressResponseDTO response = mapper.toResponseDTO(address);
  
    	return response;
    }
}