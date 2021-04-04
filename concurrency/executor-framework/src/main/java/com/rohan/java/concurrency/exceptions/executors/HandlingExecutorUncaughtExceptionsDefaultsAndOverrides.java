package com.rohan.java.concurrency.exceptions.executors;

import com.rohan.concurrency.common.factory.ThreadFactoryWithExceptionHandlerAlternator;
import com.rohan.concurrency.common.handlers.ThreadExceptionHandler;
import com.rohan.concurrency.common.tasks.ExceptionLeakingTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HandlingExecutorUncaughtExceptionsDefaultsAndOverrides {

    public static void main(String[] args) {

        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");
        Thread.setDefaultUncaughtExceptionHandler(new ThreadExceptionHandler("DEFAULT_HANDLER"));


        ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactoryWithExceptionHandlerAlternator());

        executorService.execute(new ExceptionLeakingTask());
        executorService.execute(new ExceptionLeakingTask());
        executorService.execute(new ExceptionLeakingTask());
        executorService.execute(new ExceptionLeakingTask());

        executorService.shutdown();

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}
