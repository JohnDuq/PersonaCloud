package com.example.persona.producer.rest;

import java.util.List;

import com.example.persona.producer.dto.ListaValores;
import com.example.persona.producer.util.ListaValoresConsutrctor;

public abstract class ElementosRest {

	public List<ListaValores> obtenerUrls() {
		return ListaValoresConsutrctor.construirListaAtributos(this.getClass());
	}
	
}
