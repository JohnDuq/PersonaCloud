package com.example.persona.producer.enumt;

public enum NodeName {

	PERSONA_PRODUCER("business-logic-node", "PERSONA");

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
