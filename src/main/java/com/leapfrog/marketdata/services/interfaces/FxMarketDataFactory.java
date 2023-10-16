package com.leapfrog.marketdata.services.interfaces;

import com.leapfrog.marketdata.models.FxMarketData;

public interface FxMarketDataFactory {
    FxMarketData GetNextMarketDataRecord();
}
