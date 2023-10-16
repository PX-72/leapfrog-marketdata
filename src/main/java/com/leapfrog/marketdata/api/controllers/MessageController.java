package com.leapfrog.marketdata.api.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.leapfrog.marketdata.models.FxMarketData;
import com.leapfrog.marketdata.models.MessageRequest;
import com.leapfrog.marketdata.services.FxMarketDataProvider;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final FxMarketDataProvider fxMarketDataProvider;

    private static final Gson gson = new GsonBuilder().create();

    public MessageController(KafkaTemplate<String, String> kafkaTemplate, FxMarketDataProvider fxMarketDataProvider) {
        this.kafkaTemplate = kafkaTemplate;
        this.fxMarketDataProvider = fxMarketDataProvider;
    }

    @GetMapping
    public void publish() {
        FxMarketData marketData = fxMarketDataProvider.GetNextMarketDataRecord();
        kafkaTemplate.send("leapfrog", gson.toJson(marketData));
    }
}
