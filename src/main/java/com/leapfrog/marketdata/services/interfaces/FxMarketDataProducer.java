package com.leapfrog.marketdata.services.interfaces;

import com.leapfrog.marketdata.models.FxMarketData;

public interface FxMarketDataProducer {

    void Send(FxMarketData fxMarketData);
}


