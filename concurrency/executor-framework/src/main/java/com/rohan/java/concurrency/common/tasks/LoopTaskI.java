package com.rohan.java.concurrency.common.tasks;

import com.rohan.concurrency.common.Utils;

import java.util.concurrent.CountDownLatch;

public class LoopTaskI implements Runnable {

    private static int count = 0;
    private int instanceNumber;
    private String taskId;
    private CountDownLatch latch;

    public LoopTaskI(CountDownLatch latch) {
        this.instanceNumber = ++count;
        this.taskId = "LoopTaskI-" + instanceNumber;
        this.latch = latch;
    }

    @Override
    public void run() {
        // Determining the type of thread
        boolean isDaemon = Thread.currentThread().isDaemon();
        String threadType = isDaemon ? "DAEMON" : "USER";

        String currentThreadName = Thread.currentThread().getName();

        System.out.println("######[" + currentThreadName + ", " + threadType + "] <" + taskId + "> STARTING ######");

        for (int i = 10; i > 0; i--) {
            System.out.println("[" + currentThreadName + ", " + threadType + "] <" + taskId + "TICK TICK " + i);

            System.out.println("[" + currentThreadName + "] <" + taskId + "> TICK TICK " + i);
            Utils.sleepInMillis((long) (Math.random() * 1000));
        }

        System.out.println("######[" + currentThreadName + ", " + threadType + "] <" + taskId + "> DONE ######");

        if (null != latch) {
            latch.countDown();
            System.out.println("######[" + currentThreadName + ", " + threadType + "] <" + taskId + "> LATCH_COUNT= " + latch.getCount());
        }
    }
}
