package com.rohan.java.concurrency.common.tasks;

import com.rohan.concurrency.common.Utils;

public class LoopTaskC implements Runnable {

    private static int count = 0;
    private int instanceNumber;
    private String taskId;

    public LoopTaskC() {
        this.instanceNumber = ++count;
        this.taskId = "LoopTaskC-" + instanceNumber;
    }

    @Override
    public void run() {

        System.out.println("######[" + Thread.currentThread().getName() + "] <" + taskId + "> STARTING ######");

        for (int i = 10; i > 0; i--) {
            System.out.println("[" + Thread.currentThread().getName() + "] <" + taskId + "> TICK TICK " + i);
            Utils.sleepInMillis(500);
        }

        System.out.println("######[" + Thread.currentThread().getName() + "] <" + taskId + "> DONE ######");
    }
}
