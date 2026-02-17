package com.munir.crud_pessoa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.munir.crud_pessoa.dtos.request.PessoaRequestDTO;
import com.munir.crud_pessoa.dtos.response.PessoaResponseDTO;
import com.munir.crud_pessoa.entidades.Pessoa;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
		uses = {EnderecoMapper.class, TelefoneMapper.class})
public interface PessoaMapper extends BaseMapper<Pessoa, PessoaRequestDTO, PessoaResponseDTO>{

}
