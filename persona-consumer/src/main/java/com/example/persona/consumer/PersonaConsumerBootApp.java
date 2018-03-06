package com.example.persona.consumer;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestClientException;

@SpringBootApplication
public class PersonaConsumerBootApp {

	public static void main(String[] args) throws RestClientException, IOException {
		SpringApplication.run(PersonaConsumerBootApp.class, args);
	}

}
