package com.rohan.java.concurrency.common.tasks;

import com.rohan.concurrency.common.Utils;

public class ValueReturningTaskC implements Runnable {

    private int a;
    private int b;
    private long timeout;
    private int sum;

    private static int count = 0;
    private int instanceNumber;
    private String taskId;


    public ValueReturningTaskC(int a, int b, long timeout) {
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
    }

    public int getSum() {
        return sum;
    }
}
