package com.rohan.java.concurrency.fixed;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demonstrate how to execute a task number of times within a period
 *
 * Here we have used scheduleAtFixRate, we can substitute with scheduleAtFixedDelay.
 */
public class FixedExecutionUsingScheduleAtFixRate {

    static class FixedScheduledFutureTask implements Runnable {

        private final AtomicInteger execCount = new AtomicInteger();
        private final Runnable delegate;
        private volatile ScheduledFuture<?> future;
        private final int numExecutions;

        private FixedScheduledFutureTask(Runnable delegate, int numExecutions) {
            this.delegate = delegate;
            this.numExecutions = numExecutions;
        }

        @Override
        public void run() {
            delegate.run();
            if (execCount.incrementAndGet() == numExecutions) {
                boolean interrupted = false;
                try {
                    /*
                     * Guarding task cancellation until future is assigned a valid
                     * value by the scheduled executor service
                     */
                    while (future == null) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            interrupted = true;
                        }
                    }
                    future.cancel(false);
                } finally {
                    /*
                     * Restore the interrupted status to propagate it further, because if
                     * any thread has interrupted the current thread. The interrupted status
                     * of the current thread is cleared when InterruptedException is thrown.
                     */
                    if (interrupted) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        static FixedScheduledFutureTask create(Runnable runnable, int numExecutions) {
            return new FixedScheduledFutureTask(runnable, numExecutions);
        }

        ScheduledFuture<?> run(ScheduledExecutorService executor, int delay, long period, TimeUnit unit) {
            future = executor.scheduleAtFixedRate(this, delay, period, unit);
            return future;
        }
    }


    /**
     * Driver Method:
     * <p>
     * Expected output:-
     * <p>
     * Hello!
     * Hello!
     * Hello!
     * Hello!
     * Delegated task was completed!
     */
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        FixedScheduledFutureTask task = FixedScheduledFutureTask.create(() -> System.out.println("Hello!"), 4);
        ScheduledFuture<?> executionActionTask = task.run(executorService, 0, 1, TimeUnit.SECONDS);
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        if (executionActionTask.isDone()) {
            System.out.println("Delegated task was completed!");
        }
        executorService.shutdown();
    }
}
