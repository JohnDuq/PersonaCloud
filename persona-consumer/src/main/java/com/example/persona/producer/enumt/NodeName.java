package com.example.persona.producer.enumt;

public enum NodeName {

	PERSONA_PRODUCER("persona-producer"),
	PERSONA_ZUUL_SERVICE("zuul-gateway");

	private String nodeName;

	NodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeName() {
		return nodeName;
	}

}
