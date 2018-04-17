package com.example.persona.producer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaResponse {

	private PersonaRequest personaRequest;
	private boolean transaccionExitosa;
	private String message;

}
