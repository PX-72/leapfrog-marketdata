package com.leapfrog.marketdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketDataApplication {

    public static void main(String[] args) {

        var appCtx = SpringApplication.run(MarketDataApplication.class, args);

    }

}
