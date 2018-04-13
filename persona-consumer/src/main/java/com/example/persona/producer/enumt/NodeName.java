package com.example.persona.producer.enumt;

public enum NodeName {

	PERSONA_PRODUCER("persona-producer", "PERSONA");

	private String nodeName;
	private String nodePrefix;

	NodeName(String nodeName, String nodePrefix) {
		this.nodePrefix = nodePrefix;
		this.nodeName = nodeName;
	}

	public String getNodeName() {
		return nodeName;
	}

	public String getNodePrefix() {
		return nodePrefix;
	}

}
