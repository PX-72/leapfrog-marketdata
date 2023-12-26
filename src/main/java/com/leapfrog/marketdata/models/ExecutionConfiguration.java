package com.leapfrog.marketdata.models;

public record ExecutionConfiguration (
        Integer size,
        boolean runForever,
        Integer intervalInMillis,
        Integer initialDelayInMillis
) {}
