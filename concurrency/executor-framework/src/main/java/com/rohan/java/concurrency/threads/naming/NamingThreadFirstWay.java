package com.rohan.java.concurrency.threads.naming;

import com.rohan.concurrency.common.tasks.LoopTaskB;

public class NamingThreadFirstWay {

    public static void main(String[] args) {

        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        new Thread(new LoopTaskB()).start();
        new Thread(new LoopTaskB()).start();
        new Thread(new LoopTaskB()).start();

        System.out.println("[" + currentThreadName + "] Main thread ends here...");

    }
}
