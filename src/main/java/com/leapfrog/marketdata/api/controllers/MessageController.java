package com.leapfrog.marketdata.api.controllers;

import com.leapfrog.marketdata.services.interfaces.FxMarketDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {
    private final FxMarketDataService fxMarketDataService;

    @Autowired
    public MessageController(FxMarketDataService fxMarketDataService) {
        this.fxMarketDataService = fxMarketDataService;
    }

    @GetMapping
    public void publish() {
        fxMarketDataService.PublishNext();
    }
}
