package com.example.persona.consumer;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestClientException;

@SpringBootApplication
public class ExamplePersonaConsumerApplication {

	public static void main(String[] args) throws RestClientException, IOException {
		SpringApplication.run(ExamplePersonaConsumerApplication.class, args);
	}

}
