package com.example.persona.producer.dto;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.persona.producer.entity.Person;

public class PersonConsult {

	int numeroPagina;
	int tamanoPagina;
	int offSet;

	List<Person> lPersons;

	public Pageable getPageable() {
		Integer limit = tamanoPagina != 0 ? tamanoPagina : 1;
		Integer page = offSet / limit;
		Pageable pageable = new PageRequest(page, limit);
		return pageable;
	}

	public List<Person> getlPersons() {
		return lPersons;
	}

	public void setlPersons(List<Person> lPersons) {
		this.lPersons = lPersons;
	}

	public int getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(int numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	public int getTamanoPagina() {
		return tamanoPagina;
	}

	public void setTamanoPagina(int tamanoPagina) {
		this.tamanoPagina = tamanoPagina;
	}

	public int getOffSet() {
		return offSet;
	}

	public void setOffSet(int offSet) {
		this.offSet = offSet;
	}

}
