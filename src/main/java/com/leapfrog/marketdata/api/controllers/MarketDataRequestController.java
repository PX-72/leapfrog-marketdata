package com.leapfrog.marketdata.api.controllers;

import com.leapfrog.marketdata.models.ExecutionConfiguration;
import com.leapfrog.marketdata.models.MarketDataFilter;
import com.leapfrog.marketdata.models.MarketDataRequestConfiguration;
import com.leapfrog.marketdata.services.interfaces.ExecutorRunner;
import com.leapfrog.marketdata.services.interfaces.FxMarketDataService;
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

    private final ExecutorRunner executorRunner;
    private final FxMarketDataService fxMarketDataService;

    @Autowired
    public MarketDataRequestController(ExecutorRunner executorRunner, FxMarketDataService fxMarketDataService) {
        this.executorRunner = executorRunner;
        this.fxMarketDataService = fxMarketDataService;
    }

    @PutMapping("/data-request")
    Mono<Void> replaceEmployee(@RequestBody MarketDataRequestConfiguration requestConfiguration) {
        logger.info("Market data request has been received: {}", requestConfiguration);

        executorRunner.Run(
                () -> fxMarketDataService.PublishNext(new MarketDataFilter(requestConfiguration.ccyFilter())),
                new ExecutionConfiguration(
                        requestConfiguration.size(),
                        requestConfiguration.intervalInMillis(),
                        requestConfiguration.initialDelayInMillis())
        );

        return Mono.empty();
    }
}
