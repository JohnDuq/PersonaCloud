package com.example.persona.producer.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.persona.producer.dto.PersonConsult;
import com.example.persona.producer.dto.PersonaRequest;
import com.example.persona.producer.dto.PersonaResponse;
import com.example.persona.producer.entity.Person;
import com.example.persona.producer.repository.PersonRepository;
import com.example.persona.producer.rest.PersonProducerUrl;
import com.example.persona.producer.rest.TypeConsumerProducer;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(value = PersonProducerUrl.PERSONA)
public class PersonaRestService {

	@Autowired
	private PersonRepository personRepository;

	@RequestMapping(value = PersonProducerUrl.CONSULTAR_PERSONAS, method = RequestMethod.GET, produces = TypeConsumerProducer.APPLICATION_JSON)
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

	@RequestMapping(value = PersonProducerUrl.CONSULTAR_PERSONAS_PAGINADAS, method = RequestMethod.POST, consumes = TypeConsumerProducer.APPLICATION_JSON, produces = TypeConsumerProducer.APPLICATION_JSON)
	public PersonConsult consultarPersonasPaginadas(@RequestBody PersonConsult personConsult) {
		personConsult.setlPersons(personRepository.findAll(personConsult.getPageableConstructor().construirPageable()));
		return personConsult;
	}

	@RequestMapping(value = PersonProducerUrl.GUARDAR_PERSONA, method = RequestMethod.POST, consumes = TypeConsumerProducer.APPLICATION_JSON, produces = TypeConsumerProducer.APPLICATION_JSON)
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

	@RequestMapping(value = PersonProducerUrl.ACTUALIZAR_PERSONA, method = RequestMethod.POST, consumes = TypeConsumerProducer.APPLICATION_JSON, produces = TypeConsumerProducer.APPLICATION_JSON)
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

	@RequestMapping(value = PersonProducerUrl.ELIMINAR_PERSONA, method = RequestMethod.POST, consumes = TypeConsumerProducer.APPLICATION_JSON, produces = TypeConsumerProducer.APPLICATION_JSON)
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
