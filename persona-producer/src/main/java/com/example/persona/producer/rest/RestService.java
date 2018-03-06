package com.example.persona.producer.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.persona.producer.dto.PersonConsult;
import com.example.persona.producer.dto.PersonaRequest;
import com.example.persona.producer.dto.PersonaResponse;
import com.example.persona.producer.entity.Person;
import com.example.persona.producer.repository.PersonRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class RestService {

	public static final String CONSULTAR_PERSONAS = "CONSULTAR_PERSONAS";
	public static final String CONSULTAR_PERSONAS_PAGINADAS = "CONSULTAR_PERSONAS_PAGINADAS";
	public static final String GUARDAR_PERSONA = "GUARDAR_PERSONA";
	public static final String ACTUALIZAR_PERSONA = "ACTUALIZAR_PERSONA";
	public static final String ELIMINAR_PERSONA = "ELIMINAR_PERSONA";

	@Autowired
	private PersonRepository personRepository;

	public static final String APPLICATION_JSON = "application/json";

	@RequestMapping(value = CONSULTAR_PERSONAS, method = RequestMethod.GET, produces = APPLICATION_JSON)
	@HystrixCommand(fallbackMethod = "consultarPersonasFB")
	public PersonConsult consultarPersonas() {
		PersonConsult personConsult = new PersonConsult();
		personConsult.setlPersons(personRepository.findAll());
		return personConsult;
	}

	public PersonConsult consultarPersonasFB() {
		PersonConsult personConsult = new PersonConsult();
		List<Person> lPersons = new ArrayList<>();
		Person person = new Person();
		person.setId(144);
		person.setName("fallbackMethod");
		personConsult.setlPersons(lPersons);
		return personConsult;
	}

	@RequestMapping(value = CONSULTAR_PERSONAS_PAGINADAS, method = RequestMethod.POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	public PersonConsult consultarPersonasPaginadas(@RequestBody PersonConsult personConsult) {
		personConsult.setlPersons(personRepository.findAll(personConsult.getPageableConstructor().construirPageable()));
		return personConsult;
	}

	@RequestMapping(value = GUARDAR_PERSONA, method = RequestMethod.POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	@HystrixCommand(fallbackMethod = "guardarPersonaFB")
	public PersonaResponse guardarPersona(@RequestBody PersonaRequest guardadoPersonaRequest) {
		PersonaResponse guardadoPersonaResponse = new PersonaResponse();
		try {

			Person person = new Person();
			person.setName(guardadoPersonaRequest.getName());
			personRepository.save(person);

			guardadoPersonaResponse.setTransaccionExitosa(true);
			guardadoPersonaResponse.setMessage("Guardado exitoso");

		} catch (Exception e) {
			e.printStackTrace();
			guardadoPersonaResponse.setTransaccionExitosa(false);
			guardadoPersonaResponse.setMessage("Guardado NO exitoso");
		}

		return guardadoPersonaResponse;

	}

	public PersonaResponse guardarPersonaFB(PersonaRequest guardadoPersonaRequest) {

		PersonaResponse guardadoPersonaResponse = new PersonaResponse();

		guardadoPersonaResponse.setTransaccionExitosa(false);
		guardadoPersonaResponse.setMessage("Guardado NO exitoso");

		return guardadoPersonaResponse;
	}

	@RequestMapping(value = ACTUALIZAR_PERSONA, method = RequestMethod.POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	@HystrixCommand(fallbackMethod = "actualizarPersonaFB")
	public PersonaResponse actualizarPersona(@RequestBody PersonaRequest actualizarPersonaRequest) {
		PersonaResponse guardadoPersonaResponse = new PersonaResponse();
		try {

			Person person = personRepository.findOne(actualizarPersonaRequest.getId());
			person.setName(actualizarPersonaRequest.getName());
			personRepository.save(person);

			guardadoPersonaResponse.setTransaccionExitosa(true);
			guardadoPersonaResponse.setMessage("Actualizado exitoso");

		} catch (Exception e) {
			e.printStackTrace();
			guardadoPersonaResponse.setTransaccionExitosa(false);
			guardadoPersonaResponse.setMessage("Actualizado NO exitoso");
		}

		return guardadoPersonaResponse;

	}

	public PersonaResponse actualizarPersonaFB(PersonaRequest actualizarPersonaRequest) {
		PersonaResponse guardadoPersonaResponse = new PersonaResponse();
		guardadoPersonaResponse.setTransaccionExitosa(false);
		guardadoPersonaResponse.setMessage("Actualizado NO exitoso");
		return guardadoPersonaResponse;
	}

	@RequestMapping(value = ELIMINAR_PERSONA, method = RequestMethod.POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	@HystrixCommand(fallbackMethod = "eliminarPersonaFB")
	public PersonaResponse eliminarPersona(@RequestBody PersonaRequest eliminarPersonaRequest) {
		PersonaResponse guardadoPersonaResponse = new PersonaResponse();
		try {

			personRepository.delete(eliminarPersonaRequest.getId());
			guardadoPersonaResponse.setTransaccionExitosa(true);
			guardadoPersonaResponse.setMessage("Eliminado exitoso");

		} catch (Exception e) {
			e.printStackTrace();
			guardadoPersonaResponse.setTransaccionExitosa(false);
			guardadoPersonaResponse.setMessage("Eliminado NO exitoso");
		}

		return guardadoPersonaResponse;
	}

	public PersonaResponse eliminarPersonaFB(PersonaRequest eliminarPersonaRequest) {
		PersonaResponse guardadoPersonaResponse = new PersonaResponse();
		guardadoPersonaResponse.setTransaccionExitosa(false);
		guardadoPersonaResponse.setMessage("FB: Eliminado NO exitoso");
		return guardadoPersonaResponse;
	}

}
