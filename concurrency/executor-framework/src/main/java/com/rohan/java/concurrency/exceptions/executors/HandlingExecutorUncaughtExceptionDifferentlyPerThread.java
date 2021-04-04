package com.rohan.java.concurrency.exceptions.executors;

import com.rohan.concurrency.common.tasks.ExceptionLeakingTask;
import com.rohan.concurrency.common.factory.ThreadFactoryWithExceptionHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HandlingExecutorUncaughtExceptionDifferentlyPerThread {

    public static void main(String[] args) {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        // Providing thread factory with exception handler
        ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactoryWithExceptionHandler());

        executorService.execute(new ExceptionLeakingTask());
        executorService.execute(new ExceptionLeakingTask());
        executorService.execute(new ExceptionLeakingTask());
        executorService.execute(new ExceptionLeakingTask());

        executorService.shutdown();
        System.out.println("[" + currentThreadName + "] Main thread ends here...");

    }
}
