package com.rohan.java.concurrency.threads.running;

import java.util.concurrent.TimeUnit;

/**
 * Implementing runnable;
 *
 * Here, we have a task definition, instead of thread cum task definition.
 *
 * We can just run fire these task, ignoring their control.
 *
 */
public class ThirdWayThread {

    public static void main(String[] args) {

        // We don't know which thread is going to get the cpu
        System.out.println("Main thread start here..");
        new FourthTask();
        new FourthTask();
        System.out.println("Main thread ends here..");
    }
}

class ThirdTask implements Runnable{

    private static int count  = 0;
    private int id;

    public ThirdTask() {
        this.id = ++count;
        new Thread(this).start(); // Different variant for Thread constructor
    }

    public void run() {
        for(int i = 0; i < 10; i++){
            System.out.println("<" + id + ">" + " TICK TICK " + i);
            delay();
        }
    }

    private void delay(){
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
