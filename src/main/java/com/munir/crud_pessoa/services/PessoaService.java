package com.munir.crud_pessoa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.munir.crud_pessoa.dtos.request.MailRequestDTO;
import com.munir.crud_pessoa.dtos.request.PessoaRequestDTO;
import com.munir.crud_pessoa.dtos.response.PessoaResponseDTO;
import com.munir.crud_pessoa.entidades.Pessoa;
import com.munir.crud_pessoa.enums.EmailsENUM;
import com.munir.crud_pessoa.mapper.PessoaMapper;
import com.munir.crud_pessoa.repositories.PessoaRepository;

@Service
@Transactional
public class PessoaService {
	
	@Autowired
	PessoaMapper mapper;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	PessoaRepository repository;
	
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
	
	public List<PessoaResponseDTO> find(PessoaRequestDTO requestDTO) {
	  
		List<Pessoa> listaPessoas;
		List<PessoaResponseDTO> listaResponseDTO = new ArrayList<>();
	  
		if (requestDTO == null) {
	  
			listaPessoas = repository.findAll();
			listaResponseDTO = mapper.toResponseDTOList(listaPessoas);
	  
		} else {
	  
		  //TODO: Implementar busca com filtros
		}
	  
		  //TODO revisitar hateoas
		  //addHATEOASLinks(listaPessoasDTO.get(0).getId(), listaPessoasDTO.get(0));
		  
		return listaResponseDTO;
	}
	  
	public PessoaResponseDTO save(PessoaRequestDTO requestDTO) {
	
		Pessoa pessoa = mapper.toEntity(requestDTO);		
		pessoa.getEnderecos().forEach(endereco -> endereco.setPessoa(pessoa));
		pessoa.getTelefones().forEach(telefone -> telefone.setPessoa(pessoa));
		
		repository.save(pessoa);
		
		PessoaResponseDTO responseDTO = mapper.toResponseDTO(pessoa);
		
		MailRequestDTO<Pessoa> mailDTO = new MailRequestDTO<Pessoa>(List.of(responseDTO.email()), EmailsENUM.NOVA_PESSOA_CADASTRADA, pessoa);
		emailService.send(mailDTO);	
		
		//addHATEOASLinks(pessoaDTO.getId(), pessoaDTO);
	
		return responseDTO;
	}

	@CacheEvict(value = "auditoria", allEntries = true)
    public PessoaResponseDTO update(PessoaRequestDTO requestDTO) {
    	
    	Optional<Pessoa> optionalPessoa = repository.findById(requestDTO.id());
    	
    	if(optionalPessoa.isEmpty()) 
			throw new IllegalArgumentException("Pessoa não encontrada");
    	
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
			throw new IllegalArgumentException("Pessoa não encontrada");
		
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
