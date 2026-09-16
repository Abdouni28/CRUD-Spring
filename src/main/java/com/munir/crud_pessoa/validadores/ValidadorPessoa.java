package com.munir.crud_pessoa.validadores;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.munir.crud_pessoa.dtos.request.ValidacaoDocumentoRequestDTO;
import com.munir.crud_pessoa.entidades.Endereco;
import com.munir.crud_pessoa.entidades.Pessoa;
import com.munir.crud_pessoa.entidades.Telefone;
import com.munir.crud_pessoa.enums.RegexENUM;
import com.munir.crud_pessoa.enums.TipoDocumentoENUM;
import com.munir.crud_pessoa.exceptions.PessoaValidationException;
import com.munir.crud_pessoa.repositories.PessoaRepository;
import com.munir.crud_pessoa.utils.ListUtils;
import com.munir.crud_pessoa.utils.MessagesLoader;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ValidadorPessoa implements Validador<Pessoa> {
	
	private final String EMPTY_SPACE = " ";
	private final String BLANK = "";
	private final String PARENTESIS_ESQUERDO = "(";
	private final String PARENTESIS_DIREITO = ")";
	private final String HIFEN = "-";

	private final ValidadorCamposPreenchidosEntidades<Endereco> validadorCamposEndereco;

	private final ValidadorCamposPreenchidosEntidades<Telefone> validadorCamposTelefone;
	
	private final ValidadorDocumento validadorDocumento;
	
	private final PessoaRepository repository;
	
	
	@Override
	public void validar(Pessoa pessoa) {
		
		validarNome(pessoa);
		validadorDocumento.validar(new ValidacaoDocumentoRequestDTO(pessoa.getCpf(), TipoDocumentoENUM.CPF));
		//validarCpfUnico(pessoa);
		validarEmail(pessoa);
		validarIdade(pessoa);
		validarEnderecos(pessoa);
		validarTelefones(pessoa);
	}

	private void validarNome(Pessoa pessoa) {
		
		if(!pessoa.getNome().matches(RegexENUM.APENAS_CARACTERES.getValor()))
			throw new PessoaValidationException(MessagesLoader.loadMessage("message.apenas_caracteres_nome"));
		
		List<String> nomes = Arrays.asList(pessoa.getNome().split(EMPTY_SPACE));		
		int tamanhoMinimo = 2;
		
		if(nomes.size() < tamanhoMinimo)
			throw new PessoaValidationException(MessagesLoader.loadMessage("message.nome_obrigatorio"));
		
		nomes.forEach(nome -> {
			
			if(nome.length() < tamanhoMinimo)
				throw new PessoaValidationException(MessagesLoader.loadMessage("message.tamanho_nome_obrigatorio"));
		});
		
		pessoa.setNome(pessoa.getNome().toUpperCase());
	}
	
	private void validarCpfUnico(Pessoa pessoa) {
		
		Optional<Pessoa> optionalPessoa = repository.findByCpfAndAtivaTrue(pessoa.getCpf());
		
		if(optionalPessoa.isPresent())
			throw new PessoaValidationException(MessagesLoader.loadMessage("message.cpf_duplicado"));
	}
	
	private void validarEmail(Pessoa pessoa) {
		
		if(!pessoa.getEmail().matches(RegexENUM.FORMATO_EMAIL.getValor()))
			throw new PessoaValidationException(MessagesLoader.loadMessage("message.formato_email_invalido"));
	}
	
	private void validarIdade(Pessoa pessoa) {
		
		LocalDate atual = LocalDate.now();
		LocalDate dataNascimento = pessoa.getDataNascimento();
		
		Long maioridade = 18L;
		
		if(ChronoUnit.YEARS.between(dataNascimento, atual) < maioridade)
			throw new PessoaValidationException(MessagesLoader.loadMessage("message.maioridade_obrigatoria"));
	}
	
	private void validarEnderecos(Pessoa pessoa) {
		
		if(ListUtils.isNullOrEmpty(pessoa.getEnderecos()).equals(Boolean.TRUE))
			throw new PessoaValidationException(MessagesLoader.loadMessage("message.endereco_obrigatorio"));		

		pessoa.getEnderecos().forEach(endereco -> validadorCamposEndereco.validar(endereco));
	}
	
	private void validarTelefones(Pessoa pessoa) {
		
		if(ListUtils.isNullOrEmpty(pessoa.getTelefones()).equals(Boolean.TRUE))
			throw new PessoaValidationException(MessagesLoader.loadMessage("message.telefone_obrigatorio"));

		pessoa.getTelefones().forEach(telefone -> validadorCamposTelefone.validar(telefone));
		
		pessoa.getTelefones().forEach(telefone -> {
			
			if(!telefone.getNumero().matches(RegexENUM.APENAS_NUMEROS.getValor()))
				throw new PessoaValidationException(MessagesLoader.loadMessage("message.telefone_somente_numeros"));
			
			int tamanhoMinimo = 10;
			if(telefone.getNumero().length() < tamanhoMinimo)
				throw new PessoaValidationException(MessagesLoader.loadMessage("message.tamanho_telefone_obrigatorio"));
			
			telefone.setNumero(telefone.getNumero().replace(PARENTESIS_ESQUERDO, BLANK));
			telefone.setNumero(telefone.getNumero().replace(PARENTESIS_DIREITO, BLANK));
			telefone.setNumero(telefone.getNumero().replace(HIFEN, BLANK));
		});
	}
}
