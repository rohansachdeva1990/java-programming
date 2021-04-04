package com.rohan.java.concurrency.threads.daemon;

import com.rohan.concurrency.common.tasks.LoopTaskD;

public class DaemonThreads {

    public static void main(String[] args) {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        Thread t1 = new Thread(new LoopTaskD(500), "Thread 1");
        Thread t2 = new Thread(new LoopTaskD(1000), "Thread 2");
        //t2.setDaemon(true); // only one will be able to commit
        t1.setDaemon(true); // both will be able to complete
        t1.start();
        t2.start();

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}

