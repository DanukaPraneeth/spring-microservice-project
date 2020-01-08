package com.myapps.microservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.camel.component.kafka.KafkaConfiguration;
import org.apache.camel.component.kafka.KafkaEndpoint;


@Configuration
public class KafkaEndpoints {

    @Bean
    public KafkaEndpoint endpointForProducer() {
        return createKafkaProducer("test-topic");
    }
    
    protected KafkaEndpoint createKafkaProducer(String topicName) {
        KafkaEndpoint kafkaEndpoint = new KafkaEndpoint();
        KafkaConfiguration config = new KafkaConfiguration();
        String broker = "localhost:9092";
        config.setBrokers(broker);
        config.setTopic(topicName);
        config.setKeySerializerClass("org.apache.kafka.common.serialization.StringSerializer");
        config.setKeyDeserializer("org.apache.kafka.common.serialization.StringDeserializer");
        config.setValueDeserializer("org.apache.kafka.common.serialization.StringDeserializer");
        kafkaEndpoint.setConfiguration(config);
//        kafkaEndpoint.setEndpointUriIfNotSpecified("nsbkafkaproducer:" + topicName);
        return kafkaEndpoint;
    }

}
