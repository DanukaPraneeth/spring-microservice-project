package com.myapps.microservice;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaRouteProducer extends RouteBuilder {
	
    @Autowired
	private KafkaEndpoint endpointForProducer;
		
	@Override
	public void configure() throws Exception {

		//restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);
		
        rest("/student").produces("application/json")
        	//.get("/hello/{name}")
        	//.route().transform().simple("Hello ${header.name}, Welcome to Singtel")
        	//.endRest()
        	.get("/get/{name}").route().routeId("listner")
        	.setBody(constant("Hello This is me!! ${name} ***"))
        	.to("direct:bye")
        	.endRest();
         
	    from("direct:bye").routeId("listener-id")
        .log("Receiving request from the kafka listener. Sending the request for transformation topic: ${in.body}")
        .to(endpointForProducer)
        .log("Successfully delivered the transformer topic:")
        //.end();
        ;
	}
	
	
	
//	private String route;
//
//	public KafkaRouteProducer(String route) {
//		this.route = route;
//	}
//
//	public void configure() {
//		from("direct:kafkaRoute").routeId("id").to(this.route).log("${body}");;
//	}

}
