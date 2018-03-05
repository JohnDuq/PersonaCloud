package com.example.persona.consumer.controllers;

import java.io.IOException;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Controller
public class ConsumerControllerClient {

	public void getPersona() throws RestClientException, IOException {

		String baseUrl = obtenerUrlRegistradaEurekaBalanceado();
		baseUrl += "/CONSULTAR_PERSONAS";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		System.out.println(response.getBody());
	}

	// public String obtenerUrlExplicita() {
	// String baseUrl = "http://localhost:8080";
	// return baseUrl;
	// }

	// @Autowired
	// private DiscoveryClient discoveryClient;
	//
	// public String obtenerUrlRegistradaEureka() {
	// List<ServiceInstance> instances =
	// discoveryClient.getInstances("persona-producer");
	// ServiceInstance serviceInstance = instances.get(0);
	// String baseUrl = serviceInstance.getUri().toString();
	// return baseUrl;
	// }
	//
	@Autowired
	private LoadBalancerClient loadBalancer;

	public String obtenerUrlRegistradaEurekaBalanceado() {
		ServiceInstance serviceInstance = loadBalancer.choose("persona-producer");
		System.out.println(serviceInstance.getUri());
		String baseUrl = serviceInstance.getUri().toString();
		return baseUrl;
	}

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
}