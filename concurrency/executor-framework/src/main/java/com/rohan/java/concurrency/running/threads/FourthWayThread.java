package com.rohan.java.concurrency.running.threads;

import java.util.concurrent.TimeUnit;

/**
 * Most Used..
 *
 */
public class FourthWayThread {

    public static void main(String[] args) {
        // We don't know which thread is going to get the cpu
        System.out.println("Main thread start here..");

        new Thread (new FourthTask()).start();

        Thread t = new Thread(new FourthTask());
        t.start();

        System.out.println("Main thread ends here..");
    }
}

class FourthTask implements Runnable{

    private static int count  = 0;
    private int id;

    public FourthTask() {
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
