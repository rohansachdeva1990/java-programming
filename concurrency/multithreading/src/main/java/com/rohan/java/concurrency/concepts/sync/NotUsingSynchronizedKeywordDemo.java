package com.rohan.java.concurrency.concepts.sync;

/**
 * Created by rohan on 16/03/2017.
 */
public class NotUsingSynchronizedKeywordDemo {

    private int count = 0;

    public static void main(String[] args) {
        NotUsingSynchronizedKeywordDemo obj = new NotUsingSynchronizedKeywordDemo();
        try {
            int i = 0;
            while(i++ < 20) {
                obj.doWork();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * Example
     *
     * If count = 100
     *
     * Both threads tries to increment it, the value we expect is 102, but....
     *
     * We have a scenario, in which count is incremented  in T1, but as soon as the count is assigned in T1
     * the value that thread T2 sees is still 100, and it tries to increment it and now the value
     * becomes 101 for T2 and T1, so both the threads have incremented the values to the same value 101
     *
     * So this is the classic case of threads interleaving
     *
     */
    public void doWork() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    count++; // 2 step process, not atomic
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    count++;
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
