package com.munir.crud_pessoa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.munir.crud_pessoa.dtos.response.PessoaResponseDTO;
import com.munir.crud_pessoa.entidades.Pessoa;
import com.munir.crud_pessoa.mapper.PessoaMapper;
import com.munir.crud_pessoa.repositories.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	PessoaMapper mapper;
	
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
	
	/*
	 * public List<PessoaDTO> find(PessoaDTO pessoaDTO) {
	 * 
	 * List<Pessoa> listaPessoas; List<PessoaDTO> listaPessoasDTO = new
	 * ArrayList<>();
	 * 
	 * if (pessoaDTO == null) {
	 * 
	 * listaPessoas = repository.findAll();
	 * 
	 * listaPessoasDTO = mapper.toDTOList(listaPessoas);
	 * 
	 * } else {
	 * 
	 * //TODO: Implementar busca com filtros }
	 * 
	 * addHATEOASLinks(listaPessoasDTO.get(0).getId(), listaPessoasDTO.get(0));
	 * 
	 * return listaPessoasDTO; }
	 * 
	 * public PessoaDTO save(PessoaDTO pessoaDTO) {
	 * 
	 * Pessoa pessoa = mapper.toEntity(pessoaDTO); pessoa = repository.save(pessoa);
	 * 
	 * pessoaDTO = mapper.toDTO(pessoa); addHATEOASLinks(pessoaDTO.getId(),
	 * pessoaDTO);
	 * 
	 * return pessoaDTO; }
	 * 
	 * public PessoaDTO alteraPessoa(Long idPessoa, PessoaDTO pessoaDTO) {
	 * 
	 * Pessoa pessoa = mapper.toEntity(pessoaDTO); pessoa.setId(idPessoa); pessoa =
	 * repository.save(pessoa);
	 * 
	 * pessoaDTO = mapper.toDTO(pessoa); addHATEOASLinks(pessoaDTO.getId(),
	 * pessoaDTO);
	 * 
	 * return pessoaDTO; }
	 * 
	 * public Boolean delete(Long idPessoa) {
	 * 
	 * PessoaDTO pessoaDTO = findById(idPessoa);
	 * 
	 * if(pessoaDTO != null) {
	 * 
	 * addHATEOASLinks(pessoaDTO.getId(), pessoaDTO);
	 * repository.deleteById(idPessoa);
	 * 
	 * return Boolean.TRUE; }
	 * 
	 * return Boolean.FALSE; }
	 * 
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
