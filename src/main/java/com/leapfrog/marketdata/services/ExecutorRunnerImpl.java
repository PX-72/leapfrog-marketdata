package com.leapfrog.marketdata.services;

import com.leapfrog.marketdata.models.ExecutionConfiguration;
import com.leapfrog.marketdata.services.interfaces.ExecutorRunner;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ExecutorRunnerImpl implements ExecutorRunner {
    private static final Logger logger = LoggerFactory.getLogger(ExecutorRunnerImpl.class);
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private final AtomicInteger remainingRuns = new AtomicInteger();
    private final AtomicLong delay = new AtomicLong();
    private ScheduledFuture<?> future;

    @Override
    public void Run(Runnable runnable, ExecutionConfiguration executionConfiguration) {
        remainingRuns.set(0);

        if (future != null) {
            future.cancel(false);
        }

        delay.set(executionConfiguration.intervalInMillis());
        remainingRuns.set(executionConfiguration.size());

        future = executor.scheduleWithFixedDelay(
                () -> runner(runnable),
                executionConfiguration.initialDelayInMillis(),
                delay.get(),
                TimeUnit.MILLISECONDS
        );
    }

    private void runner(Runnable runnable) {
        runnable.run();
        if (remainingRuns.decrementAndGet() <= 0 && future != null) {
            future.cancel(false);
        }
    }

    @PreDestroy
    public void shutdown() {
        executor.shutdown();
    }
}
