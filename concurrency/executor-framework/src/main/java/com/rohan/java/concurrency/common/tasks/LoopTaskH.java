package com.rohan.java.concurrency.common.tasks;

import java.util.concurrent.TimeUnit;

public class LoopTaskH implements Runnable {

    private static int count = 0;
    private int instanceNumber;
    private String taskId;
    private boolean sleepInterrupted;

    public LoopTaskH() {
        this.instanceNumber = ++count;
        this.taskId = "loopTaskH-" + instanceNumber;
    }

    public void run() {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> STARTING ######");

        // infinte loop; expecting interruption
        for (int i = 1; ; i++) {

            // ---------> What if we have a interrupted exception here.; We have handled in our strategic point
            try {
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 3000));
            } catch (InterruptedException e) {
                System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> SLEEP INTERRUPTED, DOING SOME MORE WORK .... ");
                sleepInterrupted = true;
            }

            doSomeMoreWork();

            // We need to check interrupted() because thread may be interrupted during the sleep portion or
            // at "doSomeMoreWork()". It might be possible that thread is interrupted at non blocking point before
            // reaching the blocking point. we don't want to miss these interrupts.
            // Strategic point
            if (sleepInterrupted || Thread.interrupted()) {
                System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> INTERRUPTED, CANCELLING.. ");
                break;
            }
        }

        System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> DONE ######");
    }

    private void doSomeMoreWork() {

        System.out.println("###### [" + Thread.currentThread().getName() + "] <TASK-" + taskId + "> DOING SOME WORK.....");

    }

}