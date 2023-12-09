package com.leapfrog.marketdata.services.interfaces;
import java.util.List;
import reactor.core.publisher.Mono;

public interface StaticDataService {

    Mono<List<String>> GetCurrencyPairs();
}
