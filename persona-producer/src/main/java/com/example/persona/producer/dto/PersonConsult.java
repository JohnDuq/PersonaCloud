package com.example.persona.producer.dto;

import java.util.List;

import com.example.persona.producer.entity.Person;
import com.example.persona.producer.util.PageableConstructor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonConsult {

	private PageableConstructor pageableConstructor;
	private List<Person> lPersons;

	public List<Person> getlPersons() {
		return lPersons;
	}

	public void setlPersons(List<Person> lPersons) {
		this.lPersons = lPersons;
	}

}
