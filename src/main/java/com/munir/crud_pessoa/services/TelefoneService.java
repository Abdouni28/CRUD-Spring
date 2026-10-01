package com.munir.crud_pessoa.services;

import java.text.MessageFormat;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.munir.crud_pessoa.dtos.request.TelefoneRequestDTO;
import com.munir.crud_pessoa.dtos.response.TelefoneResponseDTO;
import com.munir.crud_pessoa.entidades.Telefone;
import com.munir.crud_pessoa.mapper.TelefoneMapper;
import com.munir.crud_pessoa.repositories.TelefoneRepository;
import com.munir.crud_pessoa.utils.MessagesLoader;
import com.munir.crud_pessoa.validadores.ValidadorTelefone;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TelefoneService {
	
	private final TelefoneMapper mapper;

	private final ValidadorTelefone validador;

	private final TelefoneRepository repository;
	  
	public TelefoneResponseDTO save(TelefoneRequestDTO requestDTO) {
		
		if(requestDTO.idPessoa() == null)
			throw new IllegalArgumentException(MessageFormat.format(MessagesLoader.loadMessage("message.atributo_obrigatorio"),
																								"id_pessoa"));
	
		Telefone telefone = mapper.toEntity(requestDTO);
		
		validador.validar(telefone);
		
		repository.save(telefone);
		
		TelefoneResponseDTO responseDTO = mapper.toResponseDTO(telefone);	
	
		return responseDTO;
	}
	
	public TelefoneResponseDTO update(TelefoneRequestDTO requestDTO) {
    	
    	Optional<Telefone> optionalTelefone = repository.findById(requestDTO.id());
    	
    	if(optionalTelefone.isEmpty()) 
			throw new IllegalArgumentException(MessageFormat.format(MessagesLoader.loadMessage("message.nenhum_endereco_encontrado_by_id"),
												requestDTO.id()));				
    	
    	Telefone telefone = optionalTelefone.get();
    	
    	mapper.toEntityUpdate(requestDTO, telefone);
    	
    	telefone = repository.save(telefone);
    	
    	TelefoneResponseDTO responseDTO = mapper.toResponseDTO(telefone);
  
    	return responseDTO;
    }
}
