package com.rohan.java.concurrency.terminating.threads;


import com.rohan.java.concurrency.common.Utils;
import com.rohan.java.concurrency.common.tasks.LoopTaskE;

public class TerminatingThreadsFirstWay {

    public static void main(String[] args) {

        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        LoopTaskE task1 = new LoopTaskE();
        LoopTaskE task2 = new LoopTaskE();
        LoopTaskE task3 = new LoopTaskE();

        new Thread(task1).start();
        new Thread(task2).start();
        new Thread(task3).start();

        Utils.sleepInMillis(5000);

        // Cancelling Tasks
        task1.cancel();
        task2.cancel();
        task3.cancel();

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}
