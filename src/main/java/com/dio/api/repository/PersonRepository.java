package com.dio.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dio.api.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
