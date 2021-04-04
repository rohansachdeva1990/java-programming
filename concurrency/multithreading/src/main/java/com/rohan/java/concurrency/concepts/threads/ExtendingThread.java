package com.rohan.java.concurrency.concepts.threads;

// Ist way to create thread
class Runner extends Thread {

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            System.out.println("Hello " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ExtendingThread {
    public static void main(String[] args) {

        Runner runner1 = new Runner();
        runner1.start();

        /**
         * Note : We cannot start already started thread
         * If we call run() method, we are just calling the objects function in the
         * main thread; We are not bringing the new thread to life.
         */

        Runner runner2 = new Runner();
        runner2.start();

        // We expect interleaved output
    }
}
