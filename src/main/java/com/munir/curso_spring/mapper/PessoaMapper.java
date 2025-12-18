package com.munir.curso_spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.munir.curso_spring.entidades.Pessoa;
import com.munir.curso_spring.entidades.DTO.PessoaDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PessoaMapper extends BaseMapper<Pessoa, PessoaDTO>{

}
