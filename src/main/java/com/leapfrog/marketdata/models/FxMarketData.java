package com.leapfrog.marketdata.models;

import java.math.BigDecimal;

public record FxMarketData(String ccyPair, BigDecimal bid, BigDecimal offer, BigDecimal low, BigDecimal high) {
}
