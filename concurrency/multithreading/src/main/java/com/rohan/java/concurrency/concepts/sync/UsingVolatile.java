package com.rohan.java.concurrency.concepts.sync;

import java.util.Scanner;

/**
 * This thread might decide to cache this value (running)
 */
class Processor extends Thread{
    // Now the value will be read directly from the main memory
    //private boolean running = true;
    private volatile boolean running = true;

    @Override
    public void run() {

        /**
         * Instead of checking again and again the value of "running", the thread will
         * cache the value of the running flag and will be immune to any changes only
         * if the variable is not declared as volatile.
         *
         */
        while (running) {
            System.out.println("Hello");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gracefullly shutting down threads
     */
    public void shutdown(){
        running = false;
    }
}


/**
 *  Problems when we have shared a common
 *  data amongst the threads
 *
 *  1. Data caching ( we can save that trouble by volatile keyword)
 *  2. Thread interleaving
 */
public class UsingVolatile {

    public static void main(String[] args) {

        Processor proc1 = new Processor();
        proc1.start();

        System.out.println("Press return to stop...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // To pause execution of main thread

        // Writing to running
        proc1.shutdown();
    }
}
