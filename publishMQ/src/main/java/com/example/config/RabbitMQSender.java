package com.example.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.message.Person;


@Service
public class RabbitMQSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("${jduquer.rabbitmq.exchange}")
	private String exchange;

	@Value("${jduquer.rabbitmq.routingkey}")
	private String routingkey;

	public void send(Person person) {
		rabbitTemplate.convertAndSend(exchange, routingkey, person);
		System.out.println("Send msg = " + person);

	}
}
