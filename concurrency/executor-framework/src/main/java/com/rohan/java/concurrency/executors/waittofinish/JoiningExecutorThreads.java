package com.rohan.java.concurrency.executors.waittofinish;

import com.rohan.concurrency.common.factory.NamedThreadFactory;
import com.rohan.concurrency.common.tasks.LoopTaskI;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JoiningExecutorThreads {

    public static void main(String[] args) {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        ExecutorService executorService = Executors.newCachedThreadPool(new NamedThreadFactory());
        CountDownLatch countDownLatch = new CountDownLatch(4); // Play with latch count

        // If there are more task than latch count, those task completion is skipped and the program ends

        executorService.execute(new LoopTaskI(countDownLatch));
        executorService.execute(new LoopTaskI(countDownLatch));
        executorService.execute(new LoopTaskI(countDownLatch));
        executorService.execute(new LoopTaskI(countDownLatch));

        executorService.shutdown();

        try {
            countDownLatch.await();
            System.out.println("[" + currentThreadName + "] " + currentThreadName + " Got the signal to continue...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}
