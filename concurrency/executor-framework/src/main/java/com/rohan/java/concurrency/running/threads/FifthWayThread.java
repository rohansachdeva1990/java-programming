package com.rohan.java.concurrency.running.threads;

import java.util.concurrent.TimeUnit;

/**
 * Using inline task definition
 */
public class FifthWayThread {

    public static void main(String[] args) {

        // We don't know which thread is going to get the cpu
        System.out.println("Main thread start here..");

        Thread t = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("TICK TICK " + i);
                    delay();
                }
            }

            private void delay() {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();

        System.out.println("Main thread ends here..");
    }

}

