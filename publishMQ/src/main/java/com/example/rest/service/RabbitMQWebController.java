package com.example.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.RabbitMQSender;
import com.example.message.Person;

@RestController
@RequestMapping(value = "/rabbitmq/")
public class RabbitMQWebController {

	@Autowired
	RabbitMQSender rabbitMQSender;

	@GetMapping(value = "/publish")
	public String publish(@RequestParam("idPerson") String idPerson, @RequestParam("namePerson") String namePerson) {

		Person person = new Person();
		person.setId(Integer.parseInt(idPerson));
		person.setName(namePerson);
		rabbitMQSender.send(person);

		return "Mensaje enviado a RabbitMQ Exitosamente:" + person.toString();
	}

}
