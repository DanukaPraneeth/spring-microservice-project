package com.myapps.microservice;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class KafkaCamelRoute {
	
	@Bean(name = "KafkaRouteProducer")
    public RouteBuilder kafkaRouteProducer() {
        return new RouteBuilder() {
            public void configure() {
                from("direct:kafkaRoute").routeId("id").to("kafka:test-topic?brokers=localhost:9092").log("${body}");
                // "kafka:localhost:9092?topic=test&zookeeperHost=localhost&zookeeperPort=2181&groupId=group1&serializerClass=kafka.serializer.StringEncoder").bean(kafkaOutputBean.class);
            }
        };
    }
	
	
//	@Bean(name = "KafkaRouteProducer")
//	public RouteBuilder kafkaRouteProducer() {
//		return new KafkaRouteProducer(
//				"kafka:test-topic?brokers=localhost:9092");
////				+ "kafka:localhost:9092?topic=test-topic&groupId=testing&autoOffsetReset=earliest&consumersCount=1");
//	}

}
