package com.example.persona.producer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListaValores {

	private String nombre;
	private String codigo;

	@Override
	public String toString() {
		return "ClassPojo [nombre = " + nombre + ", codigo = " + codigo + "]";
	}

}
