package com.rohan.java.concurrency.common.tasks;

import java.util.concurrent.TimeUnit;

public class LoopTaskG implements Runnable {

    private static int count = 0;
    private int instanceNumber;
    private String taskId;

    public LoopTaskG() {
        this.instanceNumber = ++count;
        this.taskId = "loopTaskG-" + instanceNumber;
    }

    public void run() {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> STARTING ######");

        // infinte loop; expecting interruption
        for (int i = 1; ; i++) {
            try{
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 3000));
            }
            catch (InterruptedException e){
                System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> SLEEP INTERRUPTED, CANCELLING...");
                break; // End the task and terminate thread as well
            }

        }

        System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> DONE ######");
    }

}