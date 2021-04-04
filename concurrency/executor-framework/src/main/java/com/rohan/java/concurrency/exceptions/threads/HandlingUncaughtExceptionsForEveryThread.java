package com.rohan.java.concurrency.exceptions.threads;


import com.rohan.java.concurrency.common.handlers.ThreadExceptionHandler;
import com.rohan.java.concurrency.common.tasks.ExceptionLeakingTask;

public class HandlingUncaughtExceptionsForEveryThread {

    public static void main(String[] args) {

        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        // Used for all the threads
        Thread.setDefaultUncaughtExceptionHandler(new ThreadExceptionHandler("DEFAULT_HANDLER"));

        new Thread(new ExceptionLeakingTask(), "MyThread - 1").start();
        new Thread(new ExceptionLeakingTask(), "MyThread - 2").start();
        new Thread(new ExceptionLeakingTask(), "MyThread - 3").start();
        new Thread(new ExceptionLeakingTask(), "MyThread - 4").start();

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}
