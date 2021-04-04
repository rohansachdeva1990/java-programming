package com.rohan.java.concurrency.concepts.threads;

/**
 * Created by rohan on 15/03/2017.
 */


class NewRunner implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello From New runner " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * Our NewRunner class is still open to extend any class
 * We are not just passing the runner behaviour to already defined
 * thread containers
 */
public class ImplementingRunnable {

    public static void main(String[] args) {
        Thread t1 = new Thread(new NewRunner());
        Thread t2 = new Thread(new NewRunner());

        t1.start();
        t2.start();
    }
}
