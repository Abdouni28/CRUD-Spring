package com.munir.crud_pessoa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munir.crud_pessoa.dtos.request.PessoaRequestDTO;
import com.munir.crud_pessoa.dtos.response.PessoaResponseDTO;
import com.munir.crud_pessoa.services.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	
	@Autowired
	PessoaService pessoaService;
	
	@GetMapping(path = "/{id}",
				produces = { MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE,
							MediaType.APPLICATION_YAML_VALUE })
	public ResponseEntity<?> findById(@PathVariable("id") Long idPessoa) {
		
		PessoaResponseDTO pessoa = pessoaService.findById(idPessoa);
		
		if(pessoa == null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Não encontrou");
		
			//TODO revisitar mensagens
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessagesLoader.getMessage("message.pessoa_nao_encontrada"));
	
		return ResponseEntity.status(HttpStatus.OK).body(pessoa);
	}
	
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE,
							MediaType.APPLICATION_YAML_VALUE },
				consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PessoaResponseDTO>> find(@RequestBody(required = false) PessoaRequestDTO pessoaDTO) {

		List<PessoaResponseDTO> listaPessoas = pessoaService.find(pessoaDTO);

		return ResponseEntity.status(HttpStatus.OK).body(listaPessoas);
	}
	  
	
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE,
						 	  MediaType.APPLICATION_XML_VALUE,
						 	  MediaType.APPLICATION_YAML_VALUE },
				 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PessoaResponseDTO> save(@RequestBody PessoaRequestDTO requestDTO) {

		PessoaResponseDTO responseDTO = pessoaService.save(requestDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}
	  
	
	@PutMapping(produces = { MediaType.APPLICATION_JSON_VALUE,
							 MediaType.APPLICATION_XML_VALUE,
						 	 MediaType.APPLICATION_YAML_VALUE },
				consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PessoaResponseDTO> alteraPessoa(@RequestBody PessoaRequestDTO requestDTO) {

		PessoaResponseDTO responseDTO = pessoaService.alteraPessoa(requestDTO);

		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	  
	
	@DeleteMapping(path = "/{id}",
				   produces = {MediaType.APPLICATION_JSON_VALUE,
						   	   MediaType.APPLICATION_XML_VALUE,
						   	   MediaType.APPLICATION_YAML_VALUE})
	public ResponseEntity<String> delete(@PathVariable("id") Long idPessoa) {
	  
		pessoaService.delete(idPessoa);
	  
		return ResponseEntity.status(HttpStatus.OK).body("Pessoa excluída com sucesso");
		//return ResponseEntity.status(HttpStatus.OK).body(MessagesLoader.getMessage("message.pessoa_excluida_com_sucesso"));
	  
		//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pessoa não encontrada");
		//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessagesLoader.getMessage( "message.pessoa_nao_encontrada"));
	}
}
