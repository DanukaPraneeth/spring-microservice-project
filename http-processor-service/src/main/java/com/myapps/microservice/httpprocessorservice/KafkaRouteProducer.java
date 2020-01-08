package com.myapps.microservice.httpprocessorservice;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class KafkaRouteProducer extends RouteBuilder {

    @Autowired
    public KafkaEndpoint endpointForConsumer;

    @Autowired
    public KafkaEndpoint endpointForProducer;
    
    @Autowired
    private TransformProcess transformProcess;

	@Override
	public void configure() throws Exception{
		
		from(endpointForConsumer).routeId("id")
        .log("Receiving request from the kafka consumer. Sending the request for transformation: ${in.body}")
        .setHeader("correlationId",constant("1236891-s23ks2-234l"))
        .process(transformProcess)
        .log("Successfully transformed the request : ${in.body}")
        .to(endpointForProducer)
        .log("Successfully delivered the transformed request to the adapter topic:")
        .end();    
	}
	

}
