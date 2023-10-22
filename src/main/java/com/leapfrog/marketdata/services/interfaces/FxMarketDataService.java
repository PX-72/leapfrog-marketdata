package com.leapfrog.marketdata.services.interfaces;

import com.leapfrog.marketdata.models.MarketDataFilter;

public interface FxMarketDataService {
    void PublishNext();
    void PublishNext(MarketDataFilter filter);
}
