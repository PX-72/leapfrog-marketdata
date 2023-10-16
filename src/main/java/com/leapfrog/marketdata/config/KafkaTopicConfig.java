package com.leapfrog.marketdata.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${market-data.leapfrog-topic}")
    private String leapfrogTopic;

    @Bean
    public NewTopic leapfrogTopic() {
        return TopicBuilder.name(leapfrogTopic).build();
    }
}
