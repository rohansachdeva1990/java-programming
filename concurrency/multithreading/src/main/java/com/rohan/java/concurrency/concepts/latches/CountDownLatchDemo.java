package com.rohan.java.concurrency.concepts.latches;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by rohan on 17/03/2017.
 */

class Processor implements Runnable{

    private  CountDownLatch latch;

    Processor(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Started..");

        // Some useful processing
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Thread safe class
        latch.countDown();
    }
}


public class CountDownLatchDemo {

    public static void main(String[] args) {

        // Thread safe class, will count down from 3, when the latch reaches the count
        // of 0, the latch will wait
        CountDownLatch latch = new CountDownLatch(3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i = 0; i < 3; i++){
            executorService.submit(new Processor(latch));
        }
        executorService.shutdown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed!");
    }
}
