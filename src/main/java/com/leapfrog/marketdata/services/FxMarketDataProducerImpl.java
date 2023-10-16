package com.leapfrog.marketdata.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.leapfrog.marketdata.models.FxMarketData;
import com.leapfrog.marketdata.services.interfaces.FxMarketDataProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class FxMarketDataProducerImpl implements FxMarketDataProducer {

    @Value("${market-data.fx-prices-topic}")
    private String fxPricesTopic;

    private static final Gson gson = new GsonBuilder().create();

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public FxMarketDataProducerImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void Send(FxMarketData fxMarketData) {
        kafkaTemplate.send(fxPricesTopic, gson.toJson(fxMarketData));
    }
}
