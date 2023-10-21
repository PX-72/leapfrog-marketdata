package com.leapfrog.marketdata.services.interfaces;

import com.leapfrog.marketdata.models.MarketDataRequestConfiguration;
import reactor.core.publisher.Mono;

public interface MarketDataRequestService {
    Mono<Void> Run(MarketDataRequestConfiguration requestConfiguration);
}
