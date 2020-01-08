package com.myapps.microservice;

import javax.annotation.PostConstruct;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerClass {

    private static Log log = LogFactory.getLog(ControllerClass.class);
		
	@Autowired
	CamelContext camelContext;

	@Autowired
	@Qualifier("KafkaRouteProducer")
	RouteBuilder kafkaRouteProducer;
	
	@EndpointInject(uri = "direct:kafkaRoute")
	ProducerTemplate kafkaProducer;
	
	
	@PostConstruct
	public void setup() {
		try {
			camelContext.addRoutes(kafkaRouteProducer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/")
	public String defaultEnd() {
		return "Hello";
	}
	
	@GetMapping("/v1/addUSer/{msisdn}")
	public ResponseEntity<?> addNewUser(@PathVariable("msisdn") String msisdn) {
		log.info("Add Normal User Request" + msisdn);
		return new ResponseEntity<>("New User Created", HttpStatus.OK);
	}

	@DeleteMapping ("/v1/deleteUser/{msisdn}")
	public ResponseEntity<?> deleteExistingUser(@PathVariable("msisdn") String msisdn) {
		log.info("Delete Normal User Request" + msisdn);
		return new ResponseEntity<>("Existing user Deleted", HttpStatus.OK);
	}

	
	// Kafka Listener Requests
	
	@GetMapping("/v1/kafka/addUSer/{msisdn}")
	public ResponseEntity<?> addUserValidator(@PathVariable("msisdn") String msisdn) {
		
		log.info("Add new User via Kafka Request" + msisdn);

		try {
			kafkaProducer.sendBody("direct:kafkaRoute", "This request is from the user " + msisdn);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return new ResponseEntity<>("New User Creation request delivered to backend service", HttpStatus.OK);
	}


	@DeleteMapping ("/v1/kafka/deleteUser/{msisdn}")
	public ResponseEntity<?> deleteUserValidator(@PathVariable("msisdn") String msisdn) {
		log.info("Delete User via Kafka Request" + msisdn);
		try {
			kafkaProducer.sendBody("direct:kafkaRoute", "This request is from the user " + msisdn);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return new ResponseEntity<>("Delete User request delivered to backend service", HttpStatus.OK);
	}
}
