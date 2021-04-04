package com.rohan.java.concurrency.common.tasks;

import com.rohan.concurrency.common.Utils;

public class LoopTaskD implements Runnable {

    private static int count = 0;
    private int instanceNumber;
    private String taskId;
    private long sleepTime;

    public LoopTaskD(long sleepTime) {
        this.sleepTime = sleepTime;
        this.instanceNumber = ++count;
        this.taskId = "LoopTaskD-" + instanceNumber;
    }

    @Override
    public void run() {
        // Determining the type of thread
        boolean isDaemon = Thread.currentThread().isDaemon();
        String threadType = isDaemon ? "DAEMON" : "USER";

        String currentThreadName = Thread.currentThread().getName();

        System.out.println("######[" + currentThreadName + ", " + threadType +"] <" + taskId + "> STARTING ######");

        for (int i = 10; i > 0; i--) {
            System.out.println("[" + currentThreadName + ", " + threadType +"] <" + taskId + "TICK TICK " + i);

            System.out.println("[" + currentThreadName + "] <" + taskId + "> TICK TICK " + i);
            Utils.sleepInMillis(sleepTime);
        }

        System.out.println("######[" + currentThreadName + ", " + threadType +"] <" + taskId + "> DONE ######");
    }
}
