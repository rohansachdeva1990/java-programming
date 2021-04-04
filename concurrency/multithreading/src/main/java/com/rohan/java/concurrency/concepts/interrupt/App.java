package com.rohan.java.concurrency.concepts.interrupt;

import java.util.Random;

/**
 * Created by rohan on 19/03/2017.
 */
public class App {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Starting");
        Thread t1 = new Thread (()->{

            Random ran = new Random();
            for(int i = 0; i < 1E7; i++){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Interrupted");
                    break;
                }

                Math.sin(ran.nextDouble());
            }
        });
        t1.start();

        Thread.sleep(500);

        t1.interrupt();

        t1.join();
        System.out.println("Finished");

    }
}
