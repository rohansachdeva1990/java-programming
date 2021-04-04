package com.rohan.java.concurrency.common.tasks;

import com.rohan.concurrency.common.Utils;

import java.util.concurrent.TimeUnit;

public class LoopTaskB implements Runnable {

    private static int count = 0;
    private int instanceNumber;
    private String taskId;

    public LoopTaskB() {
        this.instanceNumber = ++count;
        this.taskId = "loopTaskB-" + instanceNumber;
    }

    public void run() {
        //String currentThreadName = Thread.currentThread().getName();

        // New
        Thread.currentThread().setName("Amazing Thread - " + instanceNumber);
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> STARTING ######");

        for (int i = 0; i < 10; i++) {
            System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + ">" + " TICK TICK " + i);
            Utils.sleepInMillis((long) (Math.random() * 1000));
        }

        System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> ENDING ######");
    }
}