package com.munir.crud_pessoa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.munir.crud_pessoa.entidades.Pessoa;
import com.munir.crud_pessoa.entidades.DTO.PessoaDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PessoaMapper extends BaseMapper<Pessoa, PessoaDTO>{

}
