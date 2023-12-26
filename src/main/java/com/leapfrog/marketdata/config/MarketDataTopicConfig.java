package com.leapfrog.marketdata.config;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class MarketDataTopicConfig {

    @Value("${market-data.fx-prices-topic}")
    private String fxPricesTopic;

    @Bean
    public NewTopic exampleTopic() {
        return TopicBuilder.name(fxPricesTopic)
                .partitions(1) // Only one partition
                .replicas(1)   // Only one replica, as it's a local setup
                .config("retention.ms", "10")     // retention time in milliseconds
                .config("retention.bytes", "10000") // retention size in bytes
                .build();
    }
}
