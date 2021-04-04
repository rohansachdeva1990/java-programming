package com.rohan.java.concurrency.concepts.reentrant;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by rohan on 18/03/2017.
 */
public class Runner {

    private int count = 0;

    // When a lock is acquired, the number of lock acquired during the course by the same thread is
    // incremented. And when its released, lock count is decremented.

    // It will use the same approach, only one thread at a time can acquire the lock
    private Lock lock = new ReentrantLock();

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

        // Bad
//        lock.lock();
//        increment();
//        lock.unlock();
        lock.lock();
        try {
            increment();
        } finally {
            lock.unlock();
        }
    }


    public void secondThread() throws InterruptedException {
        lock.lock();
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
