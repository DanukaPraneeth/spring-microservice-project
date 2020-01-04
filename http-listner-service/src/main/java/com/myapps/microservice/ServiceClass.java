package com.myapps.microservice;

import org.springframework.stereotype.Service;

@Service
public class ServiceClass {

	public int addUser(String msisdn) {
		return 200;
	}
	
	public int deleteUser(String msisdn) {
		return 200;
	}
}
