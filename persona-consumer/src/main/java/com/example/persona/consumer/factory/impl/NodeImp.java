package com.example.persona.consumer.factory.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

import com.example.persona.consumer.factory.NodeFactory;

@Service
public class NodeImp implements NodeFactory {

	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
	private LoadBalancerClient loadBalancer;

	@Override
	public String obtenerNodeDirecto() {
		String baseUrl = "http://localhost:8080";
		return baseUrl;
	}

	@Override
	public String obtenerNodeRegistradaEureka(String instanceNodeName) {
		List<ServiceInstance> instances = discoveryClient.getInstances(instanceNodeName);
		ServiceInstance serviceInstance = instances.get(0);
		String baseUrl = serviceInstance.getUri().toString();
		return baseUrl;
	}

	@Override
	public String obtenerNodeBalanceadoRibbonEureka(String instanceNodeName) {
		ServiceInstance serviceInstance = loadBalancer.choose(instanceNodeName);
		System.out.println("Petición atendida por " + instanceNodeName + ": " + serviceInstance.getUri());
		String baseUrl = serviceInstance.getUri().toString();
		return baseUrl;
	}

}
