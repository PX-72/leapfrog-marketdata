package com.leapfrog.marketdata.services;

import com.leapfrog.marketdata.models.CcyPairs;
import com.leapfrog.marketdata.models.FxMarketData;
import com.leapfrog.marketdata.models.MarketDataFilter;
import com.leapfrog.marketdata.services.interfaces.FxMarketDataFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class FxMarketDataFactoryImpl implements FxMarketDataFactory {

    private final Map<String, LowHigh> lowHighMap = new HashMap<>();

    private record MinMax(Double min, Double max, Integer scale) {}

    private record LowHigh(BigDecimal low, BigDecimal high) {}

    @Override
    public FxMarketData GetNextMarketDataRecord() {
        return Get(CcyPairs.MAJORS);
    }

    @Override
    public FxMarketData GetNextMarketDataRecord(MarketDataFilter filter) {
        return Get(filter.ccyPairs());
    }

    private FxMarketData Get(List<String> ccyPairs) {
        String ccyPair = ccyPairs.get(getRandomValue(ccyPairs.size()));
        MinMax minMax = getMinMaxForCcyPair(ccyPair);
        BigDecimal fxRate1 = getRandomBigDecimal(minMax.min, minMax.max, minMax.scale);
        BigDecimal fxRate2 = getRandomBigDecimal(minMax.min, minMax.max, minMax.scale);
        LowHigh currentLowHigh = getLowHigh(fxRate1, fxRate2);

        BigDecimal bid = currentLowHigh.low;
        BigDecimal ask = currentLowHigh.high;

        if (!lowHighMap.containsKey(ccyPair)) {
            lowHighMap.put(ccyPair, new LowHigh(currentLowHigh.low, currentLowHigh.high));
        } else {
            LowHigh lastLowHigh = lowHighMap.get(ccyPair);
            BigDecimal newLow = getLower(lastLowHigh.low, currentLowHigh.low);
            BigDecimal newHigh = getHigher(lastLowHigh.high, currentLowHigh.high);
            if (!same(lastLowHigh.low, newLow) || !same(lastLowHigh.high, newHigh))
            {
                LowHigh newLowHigh = new LowHigh(newLow, newHigh);
                lowHighMap.put(ccyPair, newLowHigh);
                currentLowHigh = newLowHigh;
            }
        }

        return new FxMarketData(ccyPair, bid, ask, currentLowHigh.low, currentLowHigh.high);
    }


    private static int getRandomValue(int bound) {
        Random random = new Random();
        return random.nextInt(bound);
    }

    private static BigDecimal getRandomBigDecimal(Double min, Double max, int scale) {
        Random random = new Random();
        BigDecimal minBD = BigDecimal.valueOf(min);
        BigDecimal maxBD = BigDecimal.valueOf(max);
        BigDecimal randomBigDecimal = BigDecimal.valueOf(random.nextDouble());
        BigDecimal difference = maxBD.subtract(minBD);
        BigDecimal result = minBD.add(difference.multiply(randomBigDecimal));
        return result.setScale(scale, RoundingMode.HALF_UP);
    }

    private static MinMax getMinMaxForCcyPair(String ccyPair) {
        return switch (ccyPair) {
            case CcyPairs.USDEUR -> new MinMax(0.9245, 0.9796, 4);
            case CcyPairs.USDJPY -> new MinMax(143.56, 152.12, 2);
            case CcyPairs.GBPUSD -> new MinMax(1.1954, 1.2658, 4);
            case CcyPairs.USDCHF -> new MinMax(0.8946, 0.9256, 4);
            case CcyPairs.USDCAD -> new MinMax(1.3564, 1.3865, 4);
            case CcyPairs.AUDUSD -> new MinMax(0.6234, 0.6464, 4);
            case CcyPairs.NZDUSD -> new MinMax(0.4686, 0.6754, 4);

            default -> throw new IllegalArgumentException("Invalid ccy pair: " + ccyPair);
        };
    }

    private static LowHigh getLowHigh(BigDecimal value1, BigDecimal value2) {
        if (same(value1, value2))
            return new LowHigh(value1, value1);

        return new LowHigh(getLower(value1, value2), getHigher(value1, value2));
    }

    private static boolean same(BigDecimal referenceValue, BigDecimal valueToCompare) {
        return referenceValue.compareTo(valueToCompare) == 0;
    }

    private static BigDecimal getLower(BigDecimal value1, BigDecimal value2) {
        return value1.compareTo(value2) < 0 ? value1 : value2;
    }

    private static BigDecimal getHigher(BigDecimal value1, BigDecimal value2) {
        return value1.compareTo(value2) > 0 ? value1 : value2;
    }
}
