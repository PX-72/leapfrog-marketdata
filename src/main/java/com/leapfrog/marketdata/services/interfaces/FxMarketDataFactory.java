package com.leapfrog.marketdata.services.interfaces;

import com.leapfrog.marketdata.models.FxMarketData;
import com.leapfrog.marketdata.models.MarketDataFilter;

public interface FxMarketDataFactory {
    FxMarketData GetNextMarketDataRecord();
    FxMarketData GetNextMarketDataRecord(MarketDataFilter filter);
}
