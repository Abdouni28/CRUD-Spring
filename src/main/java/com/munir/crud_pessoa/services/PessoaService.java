package com.munir.crud_pessoa.services;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.munir.crud_pessoa.dtos.request.PessoaRequestDTO;
import com.munir.crud_pessoa.dtos.request.ValidacaoPageableSortRequestDTO;
import com.munir.crud_pessoa.dtos.request.filtros_busca.FiltrosBuscaPessoaRequestDTO;
import com.munir.crud_pessoa.dtos.response.PessoaResponseDTO;
import com.munir.crud_pessoa.email_templates.EmailNovaPessoaCadastradaTemplate;
import com.munir.crud_pessoa.email_templates.EmailTemplate;
import com.munir.crud_pessoa.entidades.Pessoa;
import com.munir.crud_pessoa.exceptions.PessoaValidationException;
import com.munir.crud_pessoa.mapper.PessoaMapper;
import com.munir.crud_pessoa.repositories.PessoaRepository;
import com.munir.crud_pessoa.repositories.specifications.PessoaSpecifications;
import com.munir.crud_pessoa.security.services.UsuarioService;
import com.munir.crud_pessoa.utils.MessagesLoader;
import com.munir.crud_pessoa.validadores.ValidadorPageableSort;
import com.munir.crud_pessoa.validadores.ValidadorPessoa;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PessoaService {
	
	private final PessoaMapper mapper;	
	
	//private final ValidadorPessoa validadorPessoa;
	//private final ValidadorPageableSort validadorPageable;
	
	private final EmailService emailService;	
	private final UsuarioService usuarioService;
	
	private final PessoaRepository repository;
	
	Set<String> sortProperties = Set.of("id", "nome", "cpf", "email", "dataNascimento");
	
	public PessoaResponseDTO findById(Long idPessoa) {
		
		Optional<Pessoa> pessoa = repository.findById(idPessoa);
		
		if(pessoa.isPresent()) {
			
			PessoaResponseDTO pessoaDTO = mapper.toResponseDTO(pessoa.get());
			
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
			
			//validadorPageable.validar(new ValidacaoPageableSortRequestDTO(pageable, sortProperties));
	  
			Specification<Pessoa> specification = PessoaSpecifications.montarSpecificationsFindAll(requestDTO);
			
			listaPessoas = repository.findAll(specification, pageable).getContent();
		}
		
		listaResponseDTO = mapper.toResponseDTOList(listaPessoas);
		  
		return listaResponseDTO;
	}
	  
	public PessoaResponseDTO save(PessoaRequestDTO requestDTO) {
	
		Pessoa pessoa = mapper.toEntity(requestDTO);
		
		//validadorPessoa.validar(pessoa);
		
		pessoa.getEnderecos().forEach(endereco -> endereco.setPessoa(pessoa));
		pessoa.getTelefones().forEach(telefone -> telefone.setPessoa(pessoa));
		
		usuarioService.criarUsuario(pessoa);
		
		repository.save(pessoa);
		
		PessoaResponseDTO responseDTO = mapper.toResponseDTO(pessoa);
		
		EmailTemplate emailTemplate = new EmailNovaPessoaCadastradaTemplate(responseDTO);
		emailService.enviarParaFila(emailTemplate);	
	
		return responseDTO;
	}

	@CacheEvict(value = "auditoria", allEntries = true)
    public PessoaResponseDTO update(PessoaRequestDTO requestDTO) {
    	
    	Optional<Pessoa> optionalPessoa = repository.findById(requestDTO.id());
    	
    	if(optionalPessoa.isEmpty()) 
			throw new PessoaValidationException(MessageFormat.format(MessagesLoader.loadMessage("message.nenhuma_pessoa_encontrada_by_id"),
												requestDTO.id()));				
    	
    	Pessoa pessoa = optionalPessoa.get();
    	
    	mapper.toEntityUpdate(requestDTO, pessoa);
		
		  mapper.sincronizarEnderecos(pessoa, requestDTO.enderecos());
		  mapper.sincronizarTelefones(pessoa, requestDTO.telefones());
		 
    	
    	pessoa.getUsuario().setSenha("Teste");
    	repository.save(pessoa);
    	
    	PessoaResponseDTO responseDTO = findById(requestDTO.id());
  
    	return responseDTO;
    }
	  
	
	public void delete(Long idPessoa) {
		
		PessoaResponseDTO responseDTO = findById(idPessoa);

		if (responseDTO == null)
			throw new PessoaValidationException(MessageFormat.format(MessagesLoader.loadMessage("message.nenhuma_pessoa_encontrada_by_id"),
												idPessoa));
		
		repository.deleteById(idPessoa);
	}
}
