package com.example.message;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Person.class)
public class Person {

	private Integer id;
	private String name;

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}
	
}
