package com.leapfrog.marketdata.services;

import com.leapfrog.marketdata.models.CcyPairs;
import com.leapfrog.marketdata.services.interfaces.StaticDataService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class StaticDataServiceImpl implements StaticDataService {

    @Override
    public Mono<List<String>> GetCurrencyPairs() {
        return Mono.just(CcyPairs.MAJORS);
    }
}
