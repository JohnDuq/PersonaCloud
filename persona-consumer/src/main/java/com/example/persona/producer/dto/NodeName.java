package com.example.persona.producer.dto;

public enum NodeName {

	PERSONA_PRODUCER("persona-producer"),
	PERSONA_ZUUL_SERVICE("persona-zuul-service");

	private String nodeName;

	NodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeName() {
		return nodeName;
	}

}
