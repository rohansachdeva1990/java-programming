package com.rohan.java.concurrency.common.tasks;


import com.rohan.java.concurrency.common.Utils;
import com.rohan.java.concurrency.common.handlers.ResultListener;

public class ValueReturningTaskB implements Runnable {

    private int a;
    private int b;
    private long timeout;
    private int sum;

    private static int count = 0;
    private int instanceNumber;
    private String taskId;
    private ResultListener<Integer> resultListener;


    public ValueReturningTaskB(int a, int b, long timeout, ResultListener<Integer> resultListener) {
        this.a = a;
        this.b = b;
        this.timeout = timeout;

        this.instanceNumber = ++count;
        this.taskId = "ValReturnTaskB -" + instanceNumber;
        this.resultListener = resultListener;
    }

    public void run() {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> STARTING ######");

        Utils.sleepInMillis(timeout);
        sum = a + b;
        this.resultListener.notifyResult(sum);
        System.out.println("*******[" + currentThreadName + "] <TASK-" + taskId + "> DONE ######");

        System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> ENDING ######");
    }

}
