package com.example.persona.consumer.controllers;

import java.io.IOException;
//import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerControllerClient {

	public static final String CONSULTAR_PERSONAS = "CONSULTAR_PERSONAS";

	@RequestMapping(value = CONSULTAR_PERSONAS, method = RequestMethod.GET)
	public void consultarPersonas() throws RestClientException, IOException {

		String baseUrl = obtenerUrlExplicita();
		baseUrl += "/" + CONSULTAR_PERSONAS;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		System.out.println(response.getBody());
	}

	/*
	 * METODO PARA OBTENER URL DIRECTA
	 */
	public String obtenerUrlExplicita() {
		String baseUrl = "http://localhost:8080";
		return baseUrl;
	}

	/*
	 * METODO PARA OBTENER MICROSERVICIO REGISTRADO
	 */
	// @Autowired
	// private DiscoveryClient discoveryClient;
	// public String obtenerUrlRegistradaEureka() {
	// List<ServiceInstance> instances =
	// discoveryClient.getInstances("persona-producer");
	// ServiceInstance serviceInstance = instances.get(0);
	// String baseUrl = serviceInstance.getUri().toString();
	// return baseUrl;
	// }
	//

	/*
	 * METODO PARA OBTENER MICROSERVICIO BALANCEADO
	 */
	// @Autowired
	// private LoadBalancerClient loadBalancer;
	// public String obtenerUrlRegistradaEurekaBalanceado() {
	// ServiceInstance serviceInstance =
	// loadBalancer.choose("persona-producer");
	// System.out.println(serviceInstance.getUri());
	// String baseUrl = serviceInstance.getUri().toString();
	// return baseUrl;
	// }

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
}