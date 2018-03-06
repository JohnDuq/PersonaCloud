package com.example.persona.producer.dto;

import java.util.List;

import com.example.persona.producer.entity.Person;
import com.example.persona.producer.util.PageableConstructor;

public class PersonConsult {

	PageableConstructor pageableConstructor;
	List<Person> lPersons;

	public List<Person> getlPersons() {
		return lPersons;
	}

	public void setlPersons(List<Person> lPersons) {
		this.lPersons = lPersons;
	}

	public PageableConstructor getPageableConstructor() {
		return pageableConstructor;
	}

	public void setPageableConstructor(PageableConstructor pageableConstructor) {
		this.pageableConstructor = pageableConstructor;
	}

}
