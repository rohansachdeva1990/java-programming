package com.rohan.java.concurrency.running.executors;


import com.rohan.concurrency.common.tasks.LoopTaskA;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Special case of @{@link UsingFixedThreadPool}, with 1 thread.
 *
 * At an instance, a single thread will be executing. In sequence. No matter if we have 100 or more
 * threads in the task queue.
 *
 * If we have a shared resource, the need to synchronize is also removed as the task will be executed
 * in sequence.
 *
 *  Useful when we want to serialize access to some shared resource.
 */
public class UsingSingleThreadExecutor {

    public static void main(String[] args) {
        System.out.println("Main thread starts here..");

        // At all times total number of concurrent running task never gets exceeded.
        // Initializing phase
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(new LoopTaskA());
        executorService.submit(new LoopTaskA());
        executorService.submit(new LoopTaskA());

        // The program wont stop until and unless we call shutdown method. Never forget ! Or there
        // will be memory leak
        executorService.shutdown();

        System.out.println("Main thread ends here..");
    }

}
