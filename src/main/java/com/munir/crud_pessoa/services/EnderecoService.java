package com.munir.crud_pessoa.services;

import java.text.MessageFormat;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.munir.crud_pessoa.dtos.request.EnderecoRequestDTO;
import com.munir.crud_pessoa.dtos.response.EnderecoResponseDTO;
import com.munir.crud_pessoa.entidades.Endereco;
import com.munir.crud_pessoa.mapper.EnderecoMapper;
import com.munir.crud_pessoa.repositories.EnderecoRepository;
import com.munir.crud_pessoa.utils.MessagesLoader;
import com.munir.crud_pessoa.validadores.ValidadorEndereco;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EnderecoService {
	
	private final EnderecoMapper mapper;

	private final ValidadorEndereco validador;

	private final EnderecoRepository repository;
	  
	public EnderecoResponseDTO save(EnderecoRequestDTO requestDTO) {
		
		if(requestDTO.idPessoa() == null)
			throw new IllegalArgumentException(MessageFormat.format(MessagesLoader.loadMessage("message.atributo_obrigatorio"),
																								"id_pessoa"));
	
		Endereco endereco = mapper.toEntity(requestDTO);
		
		validador.validar(endereco);
		
		repository.save(endereco);
		
		EnderecoResponseDTO responseDTO = mapper.toResponseDTO(endereco);	
	
		return responseDTO;
	}
	
	public EnderecoResponseDTO update(EnderecoRequestDTO requestDTO) {
    	
    	Optional<Endereco> optionalEndereco = repository.findById(requestDTO.id());
    	
    	if(optionalEndereco.isEmpty()) 
			throw new IllegalArgumentException(MessageFormat.format(MessagesLoader.loadMessage("message.nenhum_endereco_encontrado_by_id"),
												requestDTO.id()));				
    	
    	Endereco endereco = optionalEndereco.get();
    	
    	mapper.toEntityUpdate(requestDTO, endereco);
    	
    	endereco = repository.save(endereco);
    	
    	EnderecoResponseDTO responseDTO = mapper.toResponseDTO(endereco);
  
    	return responseDTO;
    }
}
