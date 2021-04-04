package com.rohan.java.concurrency.common.tasks;

import com.rohan.concurrency.common.Utils;

import java.util.concurrent.Callable;

public class FactorialTaskA implements Callable<Long>{

    private static int count = 0;
    private int instanceNumber;
    private String taskId;
    private volatile boolean shutdown = false;
    private long a;
    private long sleepTime;

    private long factorial;

    public FactorialTaskA(long a, long sleepTime) {
        this.a = a;
        this.sleepTime = sleepTime;
        this.instanceNumber = ++count;
        this.taskId = "FactorialTaskA-" + instanceNumber;
    }

    @Override
    public Long call() throws Exception {        String currentThreadName = Thread.currentThread().getName();
        System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> STARTING ######");

        factorial = 1L;

        // Infinited loop
        for (long i = 1; i <=a; i++) {
            factorial *= i;
            System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> iteration - " + i + ". Intermediate Result = " + factorial);

            Utils.sleepInMillis((long) (sleepTime));
            synchronized (this) {
                if (shutdown) {
                    factorial = -1L;
                    break;
                }
            }
        }

        System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> ENDING ######");

        return factorial;
    }

    public void cancel() {
        System.out.println("###### [" + Thread.currentThread().getName() + "] <TASK-" + taskId + "> SHUTTING DOWN ######");

        /**
         * Note: When more than one thread uses the same of the object of this class; then we need to guard this
         * critical section (accessing of "shutdown" variable) with the a synchronized block ( Or mutex lock) to
         * have mutual access.
         *
         * The variable should be volatile so that it is available to the running thread every time directly from the
         * memory. (Mainly to avoid cached copies). Tells JVM no to do the optimizations. Threads get most recent value.
         *
         */
        synchronized (this) {
            this.shutdown = true;
        }

    }

}