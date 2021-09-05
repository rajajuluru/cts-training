package com.flightbookings.services;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;

@Component
public class InstanceURLHelper implements Serializable{

	@Autowired
	private LoadBalancerClient loadBalancerClient;


	public String getjwtClient() {
		ServiceInstance serviceInstance = loadBalancerClient.choose("JWT-CLIENT");
		return serviceInstance.getUri().toString();
	}
}
