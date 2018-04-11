package com.example.persona.consumer.factory;

public interface NodeFactory {

	public String obtenerNodeDirecto();

	public String obtenerNodeRegistradaEureka(String instanceNodeName);

	public String obtenerNodeBalanceadoRibbonEureka(String instanceNodeName);

}
