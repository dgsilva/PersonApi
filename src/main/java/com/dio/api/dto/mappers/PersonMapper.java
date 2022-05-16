package com.dio.api.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.dio.api.dto.request.PersonDTO;
import com.dio.api.entity.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toModel(PersonDTO dto);

    PersonDTO toDTO(Person dto);
}