package com.rohan.java.concurrency.priority;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Demonstrate on how to configure thread pools
 */
public class ConfigureThreadPool {

    private ScheduledExecutorService scheduledExecutorService;
    private ExecutorService executorService;

    public ConfigureThreadPool() {

        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        scheduledExecutorService = Executors
                .newScheduledThreadPool(availableProcessors + 2, r -> new Thread(r, "TaskExecutor-Scheduler-thread"));
    }
}
