package com.example.persona.consumer.controllers.service;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.persona.producer.dto.PersonaRequest;
import com.example.persona.producer.dto.PersonaResponse;

@RestController
public class ConsumerControllerClient {

	public static final String APPLICATION_JSON = "application/json";

	private static final String HTTP_LOCALHOST_4200 = "http://localhost:4200";

	public static final String PERSONA = "PERSONA";

	public static final String CONSULTAR_PERSONAS = "CONSULTAR_PERSONAS";
	public static final String GUARDAR_PERSONA = "GUARDAR_PERSONA";
	public static final String ACTUALIZAR_PERSONA = "ACTUALIZAR_PERSONA";
	public static final String ELIMINAR_PERSONA = "ELIMINAR_PERSONA";

	@CrossOrigin(origins = HTTP_LOCALHOST_4200)
	@RequestMapping(value = CONSULTAR_PERSONAS, method = RequestMethod.GET, produces = APPLICATION_JSON)
	public String consultarPersonas() throws RestClientException, IOException {

		String baseUrl = obtenerUrlExplicita();
		baseUrl += "/" + PERSONA + "/" + CONSULTAR_PERSONAS;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return response.getBody();
	}

	@CrossOrigin(origins = HTTP_LOCALHOST_4200)
	@RequestMapping(value = GUARDAR_PERSONA, method = RequestMethod.POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	public PersonaResponse guardarPersona(@RequestBody PersonaRequest guardadoPersonaRequest) {
		String baseUrl = obtenerUrlExplicita();
		baseUrl += "/" + PERSONA + "/" + GUARDAR_PERSONA;
		RestTemplate restTemplate = new RestTemplate();
		PersonaResponse personaResponse = null;
		try {
			personaResponse = restTemplate.postForObject(baseUrl, guardadoPersonaRequest, PersonaResponse.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return personaResponse;
	}

	@CrossOrigin(origins = HTTP_LOCALHOST_4200)
	@RequestMapping(value = ACTUALIZAR_PERSONA, method = RequestMethod.POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	public PersonaResponse actualizarPersona(@RequestBody PersonaRequest actualizarPersonaRequest) {
		String baseUrl = obtenerUrlExplicita();
		baseUrl += "/" + PERSONA + "/" + ACTUALIZAR_PERSONA;
		RestTemplate restTemplate = new RestTemplate();
		PersonaResponse personaResponse = null;
		try {
			personaResponse = restTemplate.postForObject(baseUrl, actualizarPersonaRequest, PersonaResponse.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return personaResponse;
	}

	@CrossOrigin(origins = HTTP_LOCALHOST_4200)
	@RequestMapping(value = ELIMINAR_PERSONA, method = RequestMethod.POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	public PersonaResponse eliminarPersona(@RequestBody PersonaRequest eliminarPersonaRequest) {
		String baseUrl = obtenerUrlExplicita();
		baseUrl += "/" + PERSONA + "/" + ELIMINAR_PERSONA;
		RestTemplate restTemplate = new RestTemplate();
		PersonaResponse personaResponse = null;
		try {
			personaResponse = restTemplate.postForObject(baseUrl, eliminarPersonaRequest, PersonaResponse.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return personaResponse;
	}

	/*
	 * METODO PARA OBTENER URL DIRECTA
	 */
	public String obtenerUrlExplicita() {
		String baseUrl = "http://localhost:8080";
		return baseUrl;
	}

	// /*
	// * METODO PARA OBTENER MICROSERVICIO REGISTRADO
	// */
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
	// /*
	// * METODO PARA OBTENER MICROSERVICIO BALANCEADO
	// */
	// @Autowired
	// private LoadBalancerClient loadBalancer;
	//
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