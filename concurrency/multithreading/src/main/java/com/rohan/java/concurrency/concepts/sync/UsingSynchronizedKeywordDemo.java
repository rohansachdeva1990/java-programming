package com.rohan.java.concurrency.concepts.sync;

/**
 * Created by rohan on 16/03/2017.
 */
public class UsingSynchronizedKeywordDemo {

    private int count = 0;

    /**
     * Every object in java, has an intrinsic lock (mutex) or monitor(lock). if we call synchronize method
     * of an object, then we are serializing the access to the object's state ("count") for multiple
     * threads.
     *
     * Only one thread can acquire intrinsic lock at a time
     *
     *
     * If we run the code from synchronized block, then volatile keyword is not required. It does this job for us.
     *
     *
     * Its is not good to use synchronized methods on whole method
     *
     */
    public synchronized void increment(){
        count++;
    }

    public static void main(String[] args) {
        UsingSynchronizedKeywordDemo obj = new UsingSynchronizedKeywordDemo();
        try {
            int i = 1;
            while(i++ < 20) {
                obj.doWork();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void doWork() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Count is " + count);
    }
}
