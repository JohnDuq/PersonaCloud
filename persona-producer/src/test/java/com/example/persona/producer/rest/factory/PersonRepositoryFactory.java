package com.example.persona.producer.rest.factory;

import java.util.ArrayList;
import java.util.List;

import com.example.persona.producer.entity.Person;

public class PersonRepositoryFactory {

	public static final int ID_TEST = 1;
	public static final String NAME_TEST = "TEST";

	public static List<Person> responsePersonRepositoryfindAll() {
		List<Person> lista = new ArrayList<>();
		Person person = createPerson();
		lista.add(person);
		return lista;
	}

	public static List<Person> responsePersonRepositoryfindAllPageable(int cantidad) {
		List<Person> lista = new ArrayList<>();
		Person person;
		for (int i = 0; i < cantidad; i++) {
			person = new Person();
			person.setId(i);
			person.setName(NAME_TEST + " " + i);
			lista.add(person);
		}
		return lista;
	}

	public static Person responsePersonRepositorySave() {
		return createPerson();
	}

	public static Person responsePersonRepositoryFindOne() {
		return createPerson();
	}

	private static Person createPerson() {
		Person person = new Person();
		person.setId(ID_TEST);
		person.setName(NAME_TEST);
		return person;
	}

}
