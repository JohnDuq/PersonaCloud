package com.example.persona.consumer.factory;

public interface NodeFactory {

	public String obtenerNodeDirecto();
	
	public String obtenerNodeInirecto(String instanceNodeName);

}
