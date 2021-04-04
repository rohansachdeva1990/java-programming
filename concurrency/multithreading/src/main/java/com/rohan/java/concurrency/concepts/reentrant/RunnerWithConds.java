package com.rohan.java.concurrency.concepts.reentrant;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by rohan on 18/03/2017.
 */
public class RunnerWithConds {

    private int count = 0;

    // Very similar to wait and notify

    // When a lock is acquired, the number of lock acquired during the course by the same thread is
    // incremented. And when its released, lock count is decremented.

    // It will use the same approach, only one thread at a time can acquire the lock
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * By this apporach it may appear to two threads incrementing the same value twice
     */
    private void increment() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
        System.out.println("Complete");
    }

    public void firstThread() throws InterruptedException {
        lock.lock();
        condition.await();// Relinquish the lock

        System.out.println("I am awake");

        try {
            increment();
        } finally {
            lock.unlock();
        }
    }


    public void secondThread() throws InterruptedException {
        Thread.sleep(1000);

        lock.lock();
        System.out.println("Press return key to signal");
        new Scanner(System.in).nextLine();
        System.out.println("Signalled");

        condition.signal();

        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void finished() {
        System.out.println("Count is :" + count);
    }
}
