package com.leapfrog.marketdata.api.controllers;

import com.leapfrog.marketdata.models.MessageRequest;
import com.leapfrog.marketdata.services.FxMarketDataProvider;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final FxMarketDataProvider fxMarketDataProvider;

    public MessageController(KafkaTemplate<String, String> kafkaTemplate, FxMarketDataProvider fxMarketDataProvider) {
        this.kafkaTemplate = kafkaTemplate;
        this.fxMarketDataProvider = fxMarketDataProvider;
    }


    @PostMapping
    public void publish(@RequestBody MessageRequest request) {
        kafkaTemplate.send("leapfrog", request.message());
    }
}
