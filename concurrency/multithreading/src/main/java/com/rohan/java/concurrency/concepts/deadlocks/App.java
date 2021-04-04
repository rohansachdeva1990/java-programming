package com.rohan.java.concurrency.concepts.deadlocks;

/**
 * Created by rohan on 18/03/2017.
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        //final Runner runner = new Runner();
        DeadlockSolvedRunner runner = new DeadlockSolvedRunner();

        Thread t1 = new Thread(() -> {
            try {
                runner.firstThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                runner.secondThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        runner.finished();
    }

}
