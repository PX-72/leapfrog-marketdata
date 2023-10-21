package com.leapfrog.marketdata.services;

import com.leapfrog.marketdata.models.MarketDataRequestConfiguration;
import com.leapfrog.marketdata.services.interfaces.MarketDataRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MarketDataRequestServiceImpl implements MarketDataRequestService {

    private static final Logger logger = LoggerFactory.getLogger(MarketDataRequestServiceImpl.class);
    @Override
    public Mono<Void> Run(MarketDataRequestConfiguration requestConfiguration){
        logger.info("Handling: {}", requestConfiguration);
        return Mono.empty();
    }
}
