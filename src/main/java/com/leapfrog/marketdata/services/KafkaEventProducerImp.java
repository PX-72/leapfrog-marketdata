package com.leapfrog.marketdata.services;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaEventProducerImp implements KafkaEventProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaEventProducerImp(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void Send(String topic, String payload) {
        kafkaTemplate.send(topic, payload);
    }
}
