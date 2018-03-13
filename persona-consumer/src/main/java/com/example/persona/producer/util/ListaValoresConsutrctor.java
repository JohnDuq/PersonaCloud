package com.example.persona.producer.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.example.persona.producer.dto.ListaValores;

public class ListaValoresConsutrctor {

	public static List<ListaValores> construirListaAtributos(Class obj){
		Field[] atributos = obj.getDeclaredFields();
		ListaValores listaValores;
		List<ListaValores> lista = new ArrayList<>();
		for (Field field : atributos) {
			listaValores = new ListaValores();
			listaValores.setCodigo(field.getName());
			lista.add(listaValores);
		}
		return lista;
	}
	
}
