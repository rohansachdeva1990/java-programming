package com.rohan.java.concurrency.concepts.semaphores;

import java.util.concurrent.Semaphore;

/**
 * Created by rohan on 18/03/2017.
 */

/*
    A semaphore object with one permit is like lock.
    And all the release and acquire are synchronized
 */
public class SemaphoreBasic {

    public static void main(String[] args) throws Exception {

        // Used to count number of entries. if entries are finished then it waits
        Semaphore semaphore = new Semaphore(1);

        semaphore.acquire(); // decrements the permit

        semaphore.release(); // increments the permit

        System.out.println("Available permits: " + semaphore.availablePermits());
    }

}
