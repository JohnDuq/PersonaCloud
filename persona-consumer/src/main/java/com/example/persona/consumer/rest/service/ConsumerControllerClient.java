package com.example.persona.consumer.rest.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.persona.consumer.factory.HeaderFactory;
import com.example.persona.consumer.factory.NodeFactory;
import com.example.persona.consumer.rest.OriginValid;
import com.example.persona.consumer.rest.PersonConsumerUrl;
import com.example.persona.consumer.rest.TypeConsumerConsumer;
import com.example.persona.producer.dto.PersonaRequest;
import com.example.persona.producer.dto.PersonaResponse;
import com.example.persona.producer.enumt.NodeName;

@RestController
public class ConsumerControllerClient {

	@Autowired
	private NodeFactory nodeFactory;
	@Autowired
	private HeaderFactory headerFactory;

	@CrossOrigin(origins = OriginValid.HTTP_LOCALHOST_4200)
	@RequestMapping(value = PersonConsumerUrl.CONSULTAR_PERSONAS, method = RequestMethod.GET, produces = TypeConsumerConsumer.APPLICATION_JSON)
	public String consultarPersonas() throws RestClientException, IOException {
		String baseUrl = nodeFactory.obtenerNodeRegistradaEureka(NodeName.PERSONA_PRODUCER.getNodeName());
		baseUrl +=  "/" + NodeName.PERSONA_PRODUCER.getNodePrefix() + "/" + PersonConsumerUrl.CONSULTAR_PERSONAS;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, headerFactory.getHeaders(), String.class);
		} catch (Exception ex) {
			System.out.println(ex);
			response = new ResponseEntity<>("Error de consulta", HttpStatus.CONFLICT);
		}
		return response.getBody();
	}

	@CrossOrigin(origins = OriginValid.HTTP_LOCALHOST_4200)
	@RequestMapping(value = PersonConsumerUrl.GUARDAR_PERSONA, method = RequestMethod.POST, consumes = TypeConsumerConsumer.APPLICATION_JSON, produces = TypeConsumerConsumer.APPLICATION_JSON)
	public PersonaResponse guardarPersona(@RequestBody PersonaRequest guardadoPersonaRequest) {
		String baseUrl = nodeFactory.obtenerNodeRegistradaEureka(NodeName.PERSONA_PRODUCER.getNodeName());
		baseUrl += "/" + NodeName.PERSONA_PRODUCER.getNodePrefix() + "/" + PersonConsumerUrl.GUARDAR_PERSONA;
		RestTemplate restTemplate = new RestTemplate();
		PersonaResponse personaResponse = null;
		try {
			personaResponse = restTemplate.postForObject(baseUrl, guardadoPersonaRequest, PersonaResponse.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return personaResponse;
	}

	@CrossOrigin(origins = OriginValid.HTTP_LOCALHOST_4200)
	@RequestMapping(value = PersonConsumerUrl.ACTUALIZAR_PERSONA, method = RequestMethod.POST, consumes = TypeConsumerConsumer.APPLICATION_JSON, produces = TypeConsumerConsumer.APPLICATION_JSON)
	public PersonaResponse actualizarPersona(@RequestBody PersonaRequest actualizarPersonaRequest) {
		String baseUrl = nodeFactory.obtenerNodeRegistradaEureka(NodeName.PERSONA_PRODUCER.getNodeName());
		baseUrl += "/" + NodeName.PERSONA_PRODUCER.getNodePrefix() + "/" + PersonConsumerUrl.ACTUALIZAR_PERSONA;
		RestTemplate restTemplate = new RestTemplate();
		PersonaResponse personaResponse = null;
		try {
			personaResponse = restTemplate.postForObject(baseUrl, actualizarPersonaRequest, PersonaResponse.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return personaResponse;
	}

	@CrossOrigin(origins = OriginValid.HTTP_LOCALHOST_4200)
	@RequestMapping(value = PersonConsumerUrl.ELIMINAR_PERSONA, method = RequestMethod.POST, consumes = TypeConsumerConsumer.APPLICATION_JSON, produces = TypeConsumerConsumer.APPLICATION_JSON)
	public PersonaResponse eliminarPersona(@RequestBody PersonaRequest eliminarPersonaRequest) {
		String baseUrl = nodeFactory.obtenerNodeRegistradaEureka(NodeName.PERSONA_PRODUCER.getNodeName());
		baseUrl += "/" + NodeName.PERSONA_PRODUCER.getNodePrefix() + "/" + PersonConsumerUrl.ELIMINAR_PERSONA;
		RestTemplate restTemplate = new RestTemplate();
		PersonaResponse personaResponse = null;
		try {
			personaResponse = restTemplate.postForObject(baseUrl, eliminarPersonaRequest, PersonaResponse.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return personaResponse;
	}
}