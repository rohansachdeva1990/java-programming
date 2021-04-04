package com.rohan.java.concurrency.concepts.sync;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by rohan on 16/03/2017.
 */
public class UsingAtomicIntegerDemo {

    // Using atomic integer ensure the mutual access to the count variable.
    // All increment and decrement will be atomic, but if we try to use the
    // combination of these calls, we still need to synchronize them
    private AtomicInteger count =  new AtomicInteger();
    public static void main(String[] args) {
        UsingAtomicIntegerDemo obj = new UsingAtomicIntegerDemo();
        try {
            int i = 1;
            while(i++ < 20) {
                obj.doWork();
            }
            System.out.println("Finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void doWork() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    count.incrementAndGet(); // 2 step process, not atomic
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    count.incrementAndGet();
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
