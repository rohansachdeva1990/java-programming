package com.rohan.java.concurrency.common.tasks;

import com.rohan.concurrency.common.Utils;

import java.util.concurrent.Callable;

public class CalcTaskA implements Callable<Integer> {

    private int a;
    private int b;
    private long sleeptTime;

    private static int count = 0;
    private int instanceNumber;
    private String taskId;

    public CalcTaskA(int a, int b, long sleeptTime) {
        this.a = a;
        this.b = b;
        this.sleeptTime = sleeptTime;

        this.instanceNumber = ++count;
        this.taskId = "CalcTaskA-" + instanceNumber;
    }

    public Integer call() throws Exception {

        String currentThreadName = Thread.currentThread().getName();

        System.out.println("###### [" + currentThreadName + "] <" + taskId + "> STARTING ######");
        System.out.println("[" + currentThreadName + "] <" + taskId + "> Sleeping for " + sleeptTime + " millis");

        Utils.sleepInMillis(sleeptTime);

        System.out.println("###### [" + currentThreadName + "] <" + taskId + "> DONE ######");

        return a + b;
    }
}
