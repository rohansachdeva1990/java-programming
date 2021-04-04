package com.rohan.java.concurrency.concepts.latches;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestCountDownLatch {

    ExecutorService executorService = Executors.newFixedThreadPool(1);
    private volatile boolean isUnlocked = false;
    private static final int timeoutMs = 1000;
    private static final int maxRetries = 10;

    private boolean awaitSomething() {
        boolean res = false;
        int retryCount = 0;
        CountDownLatch latch = new CountDownLatch(1);
        do {
            executorService.submit(() -> {
                try {
                    if (isUnlocked) {
                        latch.countDown();
                    }
                } catch (Exception e) {
                    System.out.println("Monitoring interrupted!!");
                }
            });
            try {
                if (latch.await(timeoutMs, TimeUnit.MILLISECONDS)) {
                    res = true;
                    return true;
                }
                System.out.println("Retrying: " + retryCount);
            } catch (InterruptedException e) {
                System.out.println("Monitoring interrupted!!");
            }

        } while (retryCount++ < maxRetries);

        if (retryCount > maxRetries) {
            System.out.println("not in time");
        }

        return false;
    }


    public void update() {
        System.out.println("Updating");
        isUnlocked = true;
    }

    public void close() {
        System.out.println("Closing");
        executorService.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        TestCountDownLatch obj = new TestCountDownLatch();

        Thread t = new Thread(new Runnable() {
            public void run() {
                System.out.println("Starting new thread");
                delay();
                obj.update();

            }

            private void delay() {
                try {
                    TimeUnit.MILLISECONDS.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        boolean res = obj.awaitSomething();
        System.out.println("Await result : " + res);

        TimeUnit.MILLISECONDS.sleep(5000);
        obj.close();
    }
}
