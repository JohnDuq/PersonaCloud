package com.example.persona.producer.dto;

public enum NodeName {

	PERSONA_PRODUCER("persona-producer");

	private String nodeName;

	NodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeName() {
		return nodeName;
	}

}
