package com.rohan.java.concurrency.common.tasks;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class FactorialTaskB implements Callable<Long> {

    private static int count = 0;
    private int instanceNumber;
    private String taskId;
    private long a;
    private long sleepTime;

    private long factorial;

    public FactorialTaskB(long a, long sleepTime) {
        this.a = a;
        this.sleepTime = sleepTime;
        this.instanceNumber = ++count;
        this.taskId = "FactorialTaskB-" + instanceNumber;
    }

    @Override
    public Long call() {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> STARTING ######");

        factorial = 1L;

        // Infinited loop
        for (long i = 1; i <= a; i++) {
            factorial *= i;
            System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> iteration - " + i + ". Intermediate Result = " + factorial);


            try {
                TimeUnit.MILLISECONDS.sleep((long) (sleepTime));
            } catch (InterruptedException e) {
                System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> Sleep interrupted. Cancelling ...");
                factorial = -1L;
                break;
            }
        }

        System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> ENDING ######");

        return factorial;
    }


}