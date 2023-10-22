package com.leapfrog.marketdata.services;

import com.leapfrog.marketdata.models.MarketDataFilter;
import com.leapfrog.marketdata.services.interfaces.FxMarketDataFactory;
import com.leapfrog.marketdata.services.interfaces.FxMarketDataProducer;
import com.leapfrog.marketdata.services.interfaces.FxMarketDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FxMarketDataServiceImpl implements FxMarketDataService {

    private final FxMarketDataFactory fxMarketDataFactory;
    private final FxMarketDataProducer fxMarketDataProducer;

    @Autowired
    public FxMarketDataServiceImpl(FxMarketDataFactory fxMarketDataFactory, FxMarketDataProducer fxMarketDataProducer) {
        this.fxMarketDataFactory = fxMarketDataFactory;
        this.fxMarketDataProducer = fxMarketDataProducer;
    }

    @Override
    public void PublishNext() {
        var marketDataItem = fxMarketDataFactory.GetNextMarketDataRecord();
        fxMarketDataProducer.Send(marketDataItem);
    }

    @Override
    public void PublishNext(MarketDataFilter filter) {
        var marketDataItem = fxMarketDataFactory.GetNextMarketDataRecord(filter);
        fxMarketDataProducer.Send(marketDataItem);
    }
}
