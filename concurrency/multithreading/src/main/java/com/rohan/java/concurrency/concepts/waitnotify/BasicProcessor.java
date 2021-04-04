package com.rohan.java.concurrency.concepts.waitnotify;

import java.util.Scanner;

/**
 * Created by rosachde on 3/17/2017.
 *
 *  Using low level java threading constructs - wait and notify
 *
 *  We will use basic Wait and Notfiy
 */
public class BasicProcessor {

    public void produce() throws InterruptedException {
        synchronized (this){
            System.out.println("Producer thread running .....");
            // It waits, in a way in which the thread does not consume system resources
            // Whereas the while(true){} -> is a tight loop
            // Whenever wait() is called, it releases the lock implicitly.
            wait();
            System.out.println("Resumed");
        }
    }

    public void consume() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(5000);

        // Lock on the same object
        synchronized (this) {
            System.out.println("Waiting for return key....");
            scanner.nextLine();
            System.out.println("Return key pressed!!");
            notify(); // It will notify only one thread that is waiting for the thread
            Thread.sleep(5000); // Now producer will not resume until, consumer leaves the synchronized block
        }
    }
}
