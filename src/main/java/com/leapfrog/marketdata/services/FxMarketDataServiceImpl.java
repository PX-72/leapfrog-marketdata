package com.leapfrog.marketdata.services;

import com.leapfrog.marketdata.models.ExecutionConfiguration;
import com.leapfrog.marketdata.models.MarketDataFilter;
import com.leapfrog.marketdata.services.interfaces.ExecutorRunner;
import com.leapfrog.marketdata.services.interfaces.FxMarketDataFactory;
import com.leapfrog.marketdata.services.interfaces.FxMarketDataProducer;
import com.leapfrog.marketdata.services.interfaces.FxMarketDataService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FxMarketDataServiceImpl implements FxMarketDataService {

    private final FxMarketDataFactory fxMarketDataFactory;
    private final FxMarketDataProducer fxMarketDataProducer;
    private final ExecutorRunner executorRunner;

    @Autowired
    public FxMarketDataServiceImpl(FxMarketDataFactory fxMarketDataFactory,
                                   FxMarketDataProducer fxMarketDataProducer,
                                   ExecutorRunner executorRunner) {
        this.fxMarketDataFactory = fxMarketDataFactory;
        this.fxMarketDataProducer = fxMarketDataProducer;
        this.executorRunner = executorRunner;
    }

    // starts up publishing process and runs during the lifecycle of the application
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        executorRunner.Run(
                this::PublishNext,
                new ExecutionConfiguration(
                        0,
                        true,
                        100,
                        3000
                )
        );
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
