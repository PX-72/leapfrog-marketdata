package com.leapfrog.marketdata.services.interfaces;

import com.leapfrog.marketdata.models.ExecutionConfiguration;

public interface ExecutorRunner {
    void Run(Runnable runnable, ExecutionConfiguration executionConfiguration);
}
