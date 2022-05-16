package com.dio.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dio.api.dto.mappers.PersonMapper;
import com.dio.api.dto.request.PersonDTO;
import com.dio.api.dto.response.MessageResponseDTO;
import com.dio.api.entity.Person;
import com.dio.api.exception.PersonNotFoundException;
import com.dio.api.repository.PersonRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	private final PersonMapper personMapper;
	
	public MessageResponseDTO create(PersonDTO personDTO) {
		Person person = personMapper.toModel(personDTO);
		Person pesrsonsave = personRepository.save(person);
		return createMessageResponse(pesrsonsave.getId(), "Foi salvo com sucesso!");
	}

	public List<PersonDTO> ListAll() {
		List<Person> allPeople = personRepository.findAll();
		return allPeople.stream()
				.map(personMapper::toDTO)
				.collect(Collectors.toList());
	}

	public PersonDTO findById(Long id) throws PersonNotFoundException {
		Person person = verifyIfExists(id);
		return personMapper.toDTO(person);
	}


	public void delete(Long id) throws PersonNotFoundException {
	  verifyIfExists(id);
		personRepository.deleteById(id);
	}

	public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
		verifyIfExists(id);
		Person person = personMapper.toModel(personDTO);
		Person pesrsonUpdate = personRepository.save(person);
		return createMessageResponse(pesrsonUpdate.getId()," Foi alterado com sucesso");
	}

	private Person verifyIfExists(Long id) throws PersonNotFoundException {
		return personRepository.findById(id)
				.orElseThrow(()-> new PersonNotFoundException(id));
	}
	private MessageResponseDTO createMessageResponse(Long id, String message) {
		return MessageResponseDTO.builder()
				.message(message + id)
				.build();
	}

}
