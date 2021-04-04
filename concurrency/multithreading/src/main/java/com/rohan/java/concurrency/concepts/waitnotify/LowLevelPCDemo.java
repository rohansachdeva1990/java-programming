package com.rohan.java.concurrency.concepts.waitnotify;

/**
 * Created by rosachde on 3/17/2017.
 */
public class LowLevelPCDemo {
    public static void main(String[] args) {

        final  LowLevelProcessor processor = new LowLevelProcessor();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consume();
                }  catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
