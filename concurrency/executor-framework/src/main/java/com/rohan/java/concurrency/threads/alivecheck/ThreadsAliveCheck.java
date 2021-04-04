package com.rohan.java.concurrency.threads.alivecheck;

import com.rohan.concurrency.common.tasks.LoopTaskC;
import com.rohan.concurrency.common.Utils;

public class ThreadsAliveCheck {

    public static void main(String[] args) {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        Thread t1 = new Thread(new LoopTaskC(), "MyThread - 1");
        Thread t2 = new Thread(new LoopTaskC(), "MyThread - 2");

        boolean t1IsAlive = t1.isAlive();
        boolean t2IsAlive = t2.isAlive();

        System.out.println("[" + currentThreadName + "] Before starting is '" + t1.getName() + "' alive :" + t1IsAlive);
        System.out.println("[" + currentThreadName + "] Before starting is '" + t2.getName() + "' alive :" + t2IsAlive);

        t1.start();
        t2.start();

        while (true) {
            Utils.sleepInMillis(300);
            t1IsAlive = t1.isAlive();
            t2IsAlive = t2.isAlive();
            System.out.println("[" + currentThreadName + "] is '" + t1.getName() + "' alive :" + t1IsAlive);
            System.out.println("[" + currentThreadName + "] is '" + t2.getName() + "' alive :" + t2IsAlive);

            if (!t1IsAlive && !t2IsAlive) {
                break;
            }
        }

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}

