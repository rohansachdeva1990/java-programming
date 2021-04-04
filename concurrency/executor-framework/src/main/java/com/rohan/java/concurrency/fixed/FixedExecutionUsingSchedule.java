package com.rohan.java.concurrency.fixed;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demonstrate how to execute a task number of times within a period
 */
public class FixedExecutionUsingSchedule {

    private AtomicInteger numExecutions;
    private final Runnable delegate;
    private volatile ScheduledFuture<?> future;
    private ScheduledExecutorService executorService;

    public FixedExecutionUsingSchedule(Runnable delegate, int numExecutions) {
        this.delegate = delegate;
        this.numExecutions = new AtomicInteger(numExecutions);
        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void perform() {
        future = executorService.schedule(this::action, 1, TimeUnit.SECONDS);
    }

    private void action() {
        if (numExecutions.decrementAndGet() >= 0) {
            delegate.run();
            perform();
        } else {
            // You can add your close hook
            System.out.println("Done!");
            if (null != future) {
                future.cancel(true);
            }
        }
    }

    public void waitAndClose() throws InterruptedException {
        executorService.awaitTermination(6, TimeUnit.SECONDS);
        if (future.isDone()) {
            System.out.println("Delegated task was completed!");
            future = null;
        }
        executorService.shutdown();
    }


    /**
     * Driver Method:
     * <p>
     * Expected output:-
     * <p>
     *
     * Hello
     * Hello
     * Hello
     * Hello
     * Done!
     * Delegated task was completed!
     *
     */
    public static void main(String[] args) throws InterruptedException {
        FixedExecutionUsingSchedule executionUsingSchedule = new FixedExecutionUsingSchedule(() -> System.out.println("Hello"), 4);
        executionUsingSchedule.perform();
        executionUsingSchedule.waitAndClose();
        System.out.println("Main exiting...");
    }
}
