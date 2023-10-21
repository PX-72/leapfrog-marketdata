package com.leapfrog.marketdata.services.interfaces;

import reactor.core.publisher.Mono;

public interface FxMarketDataService {
    Mono<Void> PublishNext();
}
