package com.rohan.java.concurrency.concepts.pc;

import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by rosachde on 3/17/2017.
 */
public class ProducerConsumerProblem {

    /**
     * Classes from concurrent package are thread safe
     * <p>
     * Idea of Producer consumer is -> One or more threads are producing items
     * and one or more threads are consuming those.
     * <p>
     * So, in general a common queue is shared to both producer and consumer
     */
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException e) {
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


    private static void producer() throws InterruptedException {

        Random random = new Random();
        while (true) {
            // Put() will patiently wait when queue is full.
            queue.put(random.nextInt(100));
        }
    }

    private static void consumer() throws InterruptedException {

        Random random = new Random();

        while (true) {
            Thread.sleep(100);
            if (random.nextInt(10) == 0) {
                //1. No sync required for queue
                //2. If there is nothing in the queue, take will patiently wait here. So, it is implicitly
                // waiting for us, until something is added to the queue
                Integer item = queue.take();

                System.out.println("Taken item: " + item + " and Queue size: " + queue.size());
            }
        }
    }
}
