package com.example.persona.producer.dto;

public class PersonaResponse {

	private boolean transaccionExitosa;
	private String message;

	public boolean isTransaccionExitosa() {
		return transaccionExitosa;
	}

	public void setTransaccionExitosa(boolean transaccionExitosa) {
		this.transaccionExitosa = transaccionExitosa;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
