package com.example.persona.producer.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.example.persona.producer.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

	List<Person> findAll();

	List<Person> findAll(Pageable pageable);

}
