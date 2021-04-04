package com.rohan.java.concurrency.common.tasks;


public class LoopTaskE implements Runnable {

    private static int count = 0;
    private int instanceNumber;
    private String taskId;
    private volatile boolean shutdown = false;

    public LoopTaskE() {
        this.instanceNumber = ++count;
        this.taskId = "loopTaskE-" + instanceNumber;
    }

    public void run() {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> STARTING ######");

        // Infinited loop
        for (int i = 0; ; i++) {
            Utils.sleepInMillis((long) (Math.random() * 3000));
            synchronized (this) {
                if (shutdown) {
                    break;
                }
            }
        }

        System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> ENDING ######");
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