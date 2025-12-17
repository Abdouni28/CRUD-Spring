package com.munir.curso_spring.services;

import static com.munir.curso_spring.utils.ObjectMapper.parseListObjects;
import static com.munir.curso_spring.utils.ObjectMapper.parseObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import com.munir.curso_spring.controllers.PessoaController;
import com.munir.curso_spring.entidades.Pessoa;
import com.munir.curso_spring.entidades.DTO.PessoaDTO;
import com.munir.curso_spring.repositories.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	PessoaRepository repository;
	
	public PessoaDTO findById(Long idPessoa) {
		
		Optional<Pessoa> pessoa = repository.findById(idPessoa);
		
		if(pessoa.isPresent()) {
			
			PessoaDTO pessoaDTO = parseObject(pessoa.get(), PessoaDTO.class);
			addHATEOASLinks(idPessoa, pessoaDTO);
			
			return pessoaDTO;
		}
				
		return null;
	}
	
	public List<PessoaDTO> find(PessoaDTO pessoaDTO) {
		
		List<Pessoa> listaPessoas;
		List<PessoaDTO> listaPessoasDTO = new ArrayList<>();
		
		if (pessoaDTO == null) {
			
			listaPessoas = repository.findAll();
			
			listaPessoasDTO = parseListObjects(listaPessoas, PessoaDTO.class);
			
		} else {
			
			//TODO: Implementar busca com filtros
		}
		
		addHATEOASLinks(listaPessoasDTO.get(0).getId(), listaPessoasDTO.get(0));
		
		return listaPessoasDTO;
	}

	public PessoaDTO save(PessoaDTO pessoaDTO) {
		
		Pessoa pessoa = parseObject(pessoaDTO, Pessoa.class);
		pessoa = repository.save(pessoa);
		
		pessoaDTO = parseObject(pessoa, PessoaDTO.class);
		addHATEOASLinks(pessoaDTO.getId(), pessoaDTO);
		
		return pessoaDTO;
	}
	
	public PessoaDTO alteraPessoa(Long idPessoa, PessoaDTO pessoaDTO) {
		
		Pessoa pessoa = parseObject(pessoaDTO, Pessoa.class);
		pessoa.setId(idPessoa);
		pessoa = repository.save(pessoa);
		
		pessoaDTO = parseObject(pessoa, PessoaDTO.class);
		addHATEOASLinks(pessoaDTO.getId(), pessoaDTO);
		
		return pessoaDTO;
	}
	
	public Boolean delete(Long idPessoa) {
		
		PessoaDTO pessoaDTO = findById(idPessoa);
		
		if(pessoaDTO != null) {
			
			addHATEOASLinks(pessoaDTO.getId(), pessoaDTO);
			repository.deleteById(idPessoa);
			
			return Boolean.TRUE;
		}		
		
		return Boolean.FALSE;
	}
	
	private void addHATEOASLinks(Long idPessoa, PessoaDTO pessoaDTO) {
		
		pessoaDTO.add(linkTo(methodOn(PessoaController.class).findById(idPessoa)).withRel("findById").withType("GET"));
		pessoaDTO.add(linkTo(methodOn(PessoaController.class).find(pessoaDTO)).withRel("findAll").withType("GET"));
		pessoaDTO.add(linkTo(methodOn(PessoaController.class).save(pessoaDTO)).withRel("create").withType("POST"));
		pessoaDTO.add(linkTo(methodOn(PessoaController.class).alteraPessoa(idPessoa, pessoaDTO)).withRel("update").withType("PUT"));
		pessoaDTO.add(linkTo(methodOn(PessoaController.class).delete(idPessoa)).withRel("delete").withType("DELETE"));
			
	}
}
