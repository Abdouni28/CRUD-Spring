package com.munir.crud_pessoa.repositories.specifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.munir.crud_pessoa.dtos.request.filtros_busca.FiltrosBuscaPessoaRequestDTO;
import com.munir.crud_pessoa.entidades.Pessoa;
import com.munir.crud_pessoa.entidades.Telefone;

import jakarta.persistence.criteria.Join;

public class PessoaSpecifications extends Specifications {

	public static Specification<Pessoa> id(Long id) {
		
        return (root, query, cb) -> id == null ? null : cb.equal(root.get("id"), id);
    }
	
	public static Specification<Pessoa> nome(String nome) {
		
        return string("nome", nome);
    }
	
	public static Specification<Pessoa> cpf(String cpf) {
		
        return string("cpf", cpf);
    }
	
	public static Specification<Pessoa> email(String email) {
		
        return string("email", email);
    }
	
	public static Specification<Pessoa> dataNascimentoDe(LocalDate dataNascimento) {
		
        return dataDe("dataNascimento", dataNascimento);
    }
	
	public static Specification<Pessoa> dataNascimentoAte(LocalDate dataNascimento) {
		
        return dataAte("dataNascimento", dataNascimento);
    }
	
	public static Specification<Pessoa> ativa(Boolean ativa) {
		
		return (root, query, cb) -> ativa == null ? null : cb.equal(root.get("ativa"), ativa);
    }
	
	public static Specification<Pessoa> idEndereco(Long id) {
		
		return  joinLong("enderecos", "id", id);
    }
	
	public static Specification<Pessoa> logradouro(String logradouro) {
		
		return  joinString("enderecos", "logradouro", logradouro);
    }
	
	public static Specification<Pessoa> nomeLogradouro(String nomeLogradouro) {
		
		return  joinString("enderecos", "nomeLogradouro", nomeLogradouro);
    }
	
	public static Specification<Pessoa> numeroEndereco(String numero) {
		
		return  joinString("enderecos", "numero", numero);
    }
	
	public static Specification<Pessoa> bairro(String bairro) {
		
		return  joinString("enderecos", "bairro", bairro);
    }
	
	public static Specification<Pessoa> cidade(String cidade) {
		
		return  joinString("enderecos", "cidade", cidade);
    }
	
	public static Specification<Pessoa> estado(String estado) {
		
		return  joinString("enderecos", "estado", estado);
    }
	
	public static Specification<Pessoa> cep(String cep) {
		
		return  joinString("enderecos", "cep", cep);
    }

	public static Specification<Pessoa> idTelefone(Long id) {
		
		return  joinLong("telefones", "id", id);
    }
	
	public static Specification<Pessoa> numeroTelefone(String numero) {
		
		return  joinString("telefones", "numero", numero);
    }
	
	public static Specification<Pessoa> idTipoTelefone(Long id) {
		
		return (root, query, cb) -> {
        	
			if(id == null) return null;
        	
        	Join<Pessoa, Telefone> telefone = root.join("telefones");
        	Join<Pessoa, Telefone> tipoTelefone = telefone.join("tipoTelefone");
        	
        	return cb.equal(tipoTelefone.get("id"), id);
        };
    }
	
	public static Specification<Pessoa> descricaoTipoTelefone(String descricao) {
		
		return (root, query, cb) -> {
        	
			if(descricao == null) return null;
        	
        	Join<Pessoa, Telefone> telefone = root.join("telefones");
        	Join<Pessoa, Telefone> tipoTelefone = telefone.join("tipoTelefone");
        	
        	return cb.like(tipoTelefone.get("descricao"), LIKE + descricao + LIKE);
        };
    }
	
	public static Specification<Pessoa> montarSpecificationsFindAll(FiltrosBuscaPessoaRequestDTO requestDTO) {
		
		Specification<Pessoa> specification = Specification.allOf(
				id(requestDTO.idPessoa()),
			    nome(requestDTO.nome()),
			    cpf(requestDTO.cpf()),
			    email(requestDTO.email()),
				dataNascimentoDe(requestDTO.dataNascimentoDe()),
				dataNascimentoAte(requestDTO.dataNascimentoAte()),
				ativa(requestDTO.ativa()),
				idEndereco(requestDTO.idEndereco()),
				logradouro(requestDTO.logradouro()),
				nomeLogradouro(requestDTO.nomeLogradouro()),
				numeroEndereco(requestDTO.numeroEndereco()),
				bairro(requestDTO.bairro()),
				cidade(requestDTO.cidade()),
				estado(requestDTO.estado()),
				cep(requestDTO.cep()),
				idTelefone(requestDTO.idTelefone()),
				numeroTelefone(requestDTO.numeroTelefone()),
				idTipoTelefone(requestDTO.idTipoTelefone()),
				descricaoTipoTelefone(requestDTO.descricaoTipoTelefone()));
		
		return specification;
	}
}
