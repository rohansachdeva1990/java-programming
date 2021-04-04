package com.rohan.java.concurrency.running.executors;

import com.rohan.java.concurrency.common.tasks.LoopTaskA;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * No concept of task wait queue.
 */
public class UsingCachedThreadPool {

    public static void main(String[] args) {
        System.out.println("Main thread starts here..");

        // At all times total number of concurrent running task never gets exceeded.
        // Initializing phase
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(new LoopTaskA());
        executorService.submit(new LoopTaskA());
        executorService.submit(new LoopTaskA());

        executorService.submit(new LoopTaskA());
        executorService.submit(new LoopTaskA());
        executorService.submit(new LoopTaskA());

        // The program wont stop until and unless we call shutdown method. Never forget ! Or there
        // will be memory leak
        executorService.shutdown();

        System.out.println("Main thread ends here..");
    }

}
