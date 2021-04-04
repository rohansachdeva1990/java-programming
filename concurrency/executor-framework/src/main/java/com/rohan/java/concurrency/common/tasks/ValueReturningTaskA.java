package com.rohan.java.concurrency.common.tasks;

import com.rohan.concurrency.common.Utils;

public class ValueReturningTaskA implements Runnable {


    private int a;
    private int b;
    private long timeout;
    private int sum;

    private static int count = 0;
    private int instanceNumber;
    private String taskId;

    // As this will be read by multiple threads
    private volatile boolean done = false;


    public ValueReturningTaskA(int a, int b, long timeout) {
        this.a = a;
        this.b = b;
        this.timeout = timeout;

        this.instanceNumber = ++count;
        this.taskId = "ValReturnTaskA-" + instanceNumber;
    }

    public void run() {

        String currentThreadName = Thread.currentThread().getName();
        System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> STARTING ######");

        Utils.sleepInMillis(timeout);
        sum = a + b;

        System.out.println("*******[" + currentThreadName + "] <TASK-" + taskId + "> DONE ######");

        // We need to synchronize on same object
        done = true;
        synchronized (this) {
            System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> NOTIFYING .......");
            this.notify();
        }

        System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> ENDING ######");
    }

    public int getSum() {
        if (!done) {

            // Q: How does the lock on self reference works from outside ?
            // A: Because from outside we are using the same object when calling the function. So "this" is valid.
            synchronized (this) {
                try {
                    System.out.println("[" + Thread.currentThread().getName() + "] ===== WAITING for result from task id: " + taskId + "....");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("[" + Thread.currentThread().getName() + "] ===== WOKEN-UP for task id: " + taskId + "....");

        return sum;
    }
}
