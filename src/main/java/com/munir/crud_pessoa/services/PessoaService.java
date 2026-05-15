package com.munir.crud_pessoa.services;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.munir.crud_pessoa.dtos.request.PessoaRequestDTO;
import com.munir.crud_pessoa.dtos.request.filtros_busca.FiltrosBuscaPessoaRequestDTO;
import com.munir.crud_pessoa.dtos.response.PessoaResponseDTO;
import com.munir.crud_pessoa.emails.Email;
import com.munir.crud_pessoa.emails.EmailNovaPessoaCadastrada;
import com.munir.crud_pessoa.entidades.Pessoa;
import com.munir.crud_pessoa.exceptions.PessoaValidationException;
import com.munir.crud_pessoa.mapper.PessoaMapper;
import com.munir.crud_pessoa.repositories.PessoaRepository;
import com.munir.crud_pessoa.repositories.specifications.PessoaSpecifications;
import com.munir.crud_pessoa.security.services.UsuarioService;
import com.munir.crud_pessoa.utils.MessagesLoader;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PessoaService {
	
	@Autowired
	MessagesLoader messagesLoader;
	
	private final PessoaMapper mapper;	
	
	//private final ValidadorPessoa validador;
	
	private final EmailService emailService;	
	private final UsuarioService usuarioService;
	
	private final PessoaRepository repository;
	
	public PessoaResponseDTO findById(Long idPessoa) {
		
		Optional<Pessoa> pessoa = repository.findById(idPessoa);
		
		if(pessoa.isPresent()) {
			
			PessoaResponseDTO pessoaDTO = mapper.toResponseDTO(pessoa.get());
			
			//TODO revisitar hateoas
			//addHATEOASLinks(idPessoa, pessoaDTO);
			
			return pessoaDTO;
		}
				
		return null;
	}
	
	public List<PessoaResponseDTO> find(FiltrosBuscaPessoaRequestDTO requestDTO, Pageable pageable) {
	  
		List<Pessoa> listaPessoas;
		List<PessoaResponseDTO> listaResponseDTO = new ArrayList<>();
	  
		if (requestDTO == null) {
	  
			listaPessoas = repository.findAll();
	  
		} else {
			
			//validador.validarSortProperties(pageable, Set.of("id", "nome", "cpf", "email", "dataNascimento"));
	  
			Specification<Pessoa> specification = PessoaSpecifications.montarSpecificationsFindAll(requestDTO);
			
			listaPessoas = repository.findAll(specification, pageable).getContent();
		}
	  
		  //TODO revisitar hateoas
		  //addHATEOASLinks(listaPessoasDTO.get(0).getId(), listaPessoasDTO.get(0));
		
		listaResponseDTO = mapper.toResponseDTOList(listaPessoas);
		  
		return listaResponseDTO;
	}
	  
	public PessoaResponseDTO save(PessoaRequestDTO requestDTO) {
	
		Pessoa pessoa = mapper.toEntity(requestDTO);
		
		//validador.validar(pessoa);
		
		pessoa.getEnderecos().forEach(endereco -> endereco.setPessoa(pessoa));
		pessoa.getTelefones().forEach(telefone -> telefone.setPessoa(pessoa));
		
		String senha = usuarioService.criarUsuario(pessoa, Boolean.TRUE, Boolean.FALSE);
		
		repository.save(pessoa);
		
		PessoaResponseDTO responseDTO = mapper.toResponseDTO(pessoa);
		
		Email email = new EmailNovaPessoaCadastrada(responseDTO.nome(), responseDTO.usuario().nomeUsuario(), senha, Arrays.asList(responseDTO.email()));
		emailService.send(email);	
		
		//addHATEOASLinks(pessoaDTO.getId(), pessoaDTO);
	
		return responseDTO;
	}

	@CacheEvict(value = "auditoria", allEntries = true)
    public PessoaResponseDTO update(PessoaRequestDTO requestDTO) {
    	
    	Optional<Pessoa> optionalPessoa = repository.findById(requestDTO.id());
    	
    	if(optionalPessoa.isEmpty()) 
			throw new PessoaValidationException(MessageFormat.format(messagesLoader.loadMessage("message.nenhuma_pessoa_encontrada_by_id"),
												requestDTO.id()));				
    	
    	Pessoa pessoa = optionalPessoa.get();
    	
    	mapper.toEntityUpdate(requestDTO, pessoa);
    	mapper.sincronizarEnderecos(pessoa, requestDTO.enderecos());
    	mapper.sincronizarTelefones(pessoa, requestDTO.telefones());
  
    	repository.save(pessoa);
    	
    	PessoaResponseDTO responseDTO = findById(requestDTO.id());
	  
    	//addHATEOASLinks(pessoaDTO.getId(), pessoaDTO);
  
    	return responseDTO;
    }
	  
	
	public void delete(Long idPessoa) {
		
		PessoaResponseDTO responseDTO = findById(idPessoa);

		if (responseDTO == null)
			throw new PessoaValidationException(MessageFormat.format(messagesLoader.loadMessage("message.nenhuma_pessoa_encontrada_by_id"),
												idPessoa));
		
		//addHATEOASLinks(pessoaDTO.getId(), pessoaDTO);
		repository.deleteById(idPessoa);
	}
	  
	/*
	 * private void addHATEOASLinks(Long idPessoa, PessoaDTO pessoaDTO) {
	 * 
	 * pessoaDTO.add(linkTo(methodOn(PessoaController.class).findById(idPessoa)).
	 * withRel("findById").withType("GET"));
	 * pessoaDTO.add(linkTo(methodOn(PessoaController.class).find(pessoaDTO)).
	 * withRel("findAll").withType("GET"));
	 * pessoaDTO.add(linkTo(methodOn(PessoaController.class).save(pessoaDTO)).
	 * withRel("create").withType("POST"));
	 * pessoaDTO.add(linkTo(methodOn(PessoaController.class).alteraPessoa(idPessoa,
	 * pessoaDTO)).withRel("update").withType("PUT"));
	 * pessoaDTO.add(linkTo(methodOn(PessoaController.class).delete(idPessoa)).
	 * withRel("delete").withType("DELETE"));
	 * 
	 * }
	 */
}
