package com.leapfrog.marketdata.services;

import com.leapfrog.marketdata.models.FxMarketData;

public interface FxMarketDataProvider {
    FxMarketData GetNextMarketDataRecord();
}
