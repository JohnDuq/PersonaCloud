package com.example.persona.consumer.factory;

import java.io.IOException;

import org.springframework.http.HttpEntity;

public interface HeaderFactory {

	public HttpEntity<?> getHeaders() throws IOException;
	
}
