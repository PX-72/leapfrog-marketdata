package com.leapfrog.marketdata.models;

public record ExecutionConfiguration (
        Integer size,
        Integer intervalInMillis,
        Integer initialDelayInMillis
) {}
