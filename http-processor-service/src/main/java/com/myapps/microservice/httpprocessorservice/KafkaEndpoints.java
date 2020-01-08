package com.myapps.microservice.httpprocessorservice;

import org.apache.camel.component.kafka.KafkaConfiguration;
import org.apache.camel.component.kafka.KafkaEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaEndpoints {

    @Bean
    public KafkaEndpoint endpointForProducer() {
        return createKafkaProducer("test-topic-result");
    }
    
    @Bean
    public KafkaEndpoint endpointForConsumer() {
        return createKafkaConsumer("test-topic");
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
        return kafkaEndpoint;
    }
    
    protected KafkaEndpoint createKafkaConsumer(String topicName) {
        KafkaEndpoint kafkaEndpoint = new KafkaEndpoint();
        KafkaConfiguration config = new KafkaConfiguration();
        String broker = "localhost:9092";
        config.setBrokers(broker);
        config.setTopic(topicName);
        config.setKeySerializerClass("org.apache.kafka.common.serialization.StringSerializer");
        config.setKeyDeserializer("org.apache.kafka.common.serialization.StringDeserializer");
        config.setValueDeserializer("org.apache.kafka.common.serialization.StringDeserializer");
        kafkaEndpoint.setConfiguration(config);
        return kafkaEndpoint;
    }
}
