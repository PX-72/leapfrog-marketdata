package com.leapfrog.marketdata.api.controllers;

import com.leapfrog.marketdata.models.MarketDataRequestConfiguration;
import com.leapfrog.marketdata.services.interfaces.MarketDataRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/market-data")
public class MarketDataRequestController {

    private static final Logger logger = LoggerFactory.getLogger(MarketDataRequestController.class);

    private final MarketDataRequestService marketDataRequestService;

    @Autowired
    public MarketDataRequestController(MarketDataRequestService marketDataRequestService) {
        this.marketDataRequestService = marketDataRequestService;
    }

    @PutMapping("/data-request")
    Mono<Void> replaceEmployee(@RequestBody MarketDataRequestConfiguration requestConfiguration) {
        logger.info("Market data request has been received: {}", requestConfiguration);
        return marketDataRequestService.Run(requestConfiguration);
    }


}
