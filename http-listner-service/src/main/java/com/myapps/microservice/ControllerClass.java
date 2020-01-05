package com.myapps.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerClass {

	@Autowired
	private ServiceClass serviceClass;
	
	@RequestMapping("/")
	public String defaultEnd() {
		return "Hello";
	}
	
	@GetMapping("/v1/addUSer/{msisdn}")
	public ResponseEntity<?> addNewUser(@PathVariable("msisdn") String msisdn) {
		return new ResponseEntity<>("New User Created", HttpStatus.OK);
	}

	@DeleteMapping ("/v1/deleteUser/{msisdn}")
	public ResponseEntity<?> deleteExistingUser(@PathVariable("msisdn") String msisdn) {
		return new ResponseEntity<>("Existing user Deleted", HttpStatus.OK);
	}

	
	// Kafka Listener Requests
	
	@GetMapping("/v1/kafka/addUSer/{msisdn}")
	public ResponseEntity<?> addUserValidator(@PathVariable("msisdn") String msisdn) {
		return new ResponseEntity<>(serviceClass.addUser(msisdn), HttpStatus.OK);
	}


	@DeleteMapping ("/v1/kafka/deleteUser/{msisdn}")
	public ResponseEntity<?> deleteUserValidator(@PathVariable("msisdn") String msisdn) {
		return new ResponseEntity<>(serviceClass.deleteUser(msisdn), HttpStatus.OK);
	}
}
