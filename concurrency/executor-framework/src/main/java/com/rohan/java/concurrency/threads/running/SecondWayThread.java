package com.rohan.java.concurrency.threads.running;

import java.util.concurrent.TimeUnit;

/**
 * This technique gives more control over thread executions
 */
public class SecondWayThread {

    public static void main(String[] args) {
        System.out.println("Main thread start here..");
        new SecondTask().start();
        Thread t = new SecondTask();
        t.start();
        System.out.println("Main thread ends here..");
    }
}

class SecondTask extends Thread {

    private static int count  = 0;
    private int id;

    public SecondTask() {
        this.id = ++count;
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
