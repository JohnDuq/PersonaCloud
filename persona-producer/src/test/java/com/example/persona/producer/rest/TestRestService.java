package com.example.persona.producer.rest;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.persona.producer.PersonaProducerBootAppTests;
import com.example.persona.producer.dto.PersonConsult;
import com.example.persona.producer.dto.PersonaRequest;
import com.example.persona.producer.dto.PersonaResponse;
import com.example.persona.producer.entity.Person;
import com.example.persona.producer.repository.PersonRepository;
import com.example.persona.producer.rest.factory.PersonRepositoryFactory;
import com.example.persona.producer.util.PageableConstructor;

public class TestRestService extends PersonaProducerBootAppTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@InjectMocks
	RestService restService;

	@Mock
	PersonRepository personRepository;

	private final int pagina = 1;
	private final int cantidadRegistros = 10;

	@Before
	public void setup() {
		MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MockitoAnnotations.initMocks(this);
		Mockito.when(personRepository.findAll()).thenReturn(PersonRepositoryFactory.responsePersonRepositoryfindAll());
		Mockito.when(personRepository.findAll(Mockito.any(Pageable.class)))
				.thenReturn(PersonRepositoryFactory.responsePersonRepositoryfindAllPageable(cantidadRegistros));
		Mockito.when(personRepository.save(Mockito.any(Person.class)))
				.thenReturn(PersonRepositoryFactory.responsePersonRepositorySave());
		Mockito.when(personRepository.findOne(Mockito.anyInt()))
				.thenReturn(PersonRepositoryFactory.responsePersonRepositoryFindOne());
	}

	@Test
	public void testConsultarPersonas() throws Exception {
		PersonConsult reponse = restService.consultarPersonas();
		assertTrue(!reponse.getlPersons().isEmpty());
		assertTrue(reponse.getlPersons().get(0).getId().equals(PersonRepositoryFactory.ID_TEST));
		assertTrue(reponse.getlPersons().get(0).getName().equals(PersonRepositoryFactory.NAME_TEST));
	}

	@Test
	public void testGuardarPersona() throws Exception {
		PersonaRequest personaRequest = new PersonaRequest();
		personaRequest.setId(PersonRepositoryFactory.ID_TEST);
		personaRequest.setName(PersonRepositoryFactory.NAME_TEST);
		PersonaResponse reponse = restService.guardarPersona(personaRequest);
		assertTrue(reponse.isTransaccionExitosa());
	}

	@Test
	public void testActualizarPersona() throws Exception {
		PersonaRequest personaRequest = new PersonaRequest();
		personaRequest.setId(PersonRepositoryFactory.ID_TEST);
		personaRequest.setName(PersonRepositoryFactory.NAME_TEST);
		PersonaResponse reponse = restService.actualizarPersona(personaRequest);
		assertTrue(reponse.isTransaccionExitosa());
	}

	@Test
	public void testEliminarPersona() throws Exception {
		PersonaRequest personaRequest = new PersonaRequest();
		personaRequest.setId(PersonRepositoryFactory.ID_TEST);
		personaRequest.setName(PersonRepositoryFactory.NAME_TEST);
		PersonaResponse reponse = restService.eliminarPersona(personaRequest);
		assertTrue(reponse.isTransaccionExitosa());
	}

}
