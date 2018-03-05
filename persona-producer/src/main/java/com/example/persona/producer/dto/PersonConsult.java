package com.example.persona.producer.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.persona.producer.entity.Person;

public class PersonConsult {

	Pageable pageable;
	List<Person> lPersons;

	public List<Person> getlPersons() {
		return lPersons;
	}

	public void setlPersons(List<Person> lPersons) {
		this.lPersons = lPersons;
	}

	public Pageable getPageable() {
		return pageable;
	}

	public void setPageable(Pageable pageable) {
		this.pageable = pageable;
	}

}
