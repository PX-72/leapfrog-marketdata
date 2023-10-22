package com.leapfrog.marketdata.models;

import java.util.List;

public record MarketDataRequestConfiguration (
    String configurationId,
    Integer size,
    Integer intervalInMillis,
    Integer initialDelayInMillis,
    List<String> ccyFilter
){}
