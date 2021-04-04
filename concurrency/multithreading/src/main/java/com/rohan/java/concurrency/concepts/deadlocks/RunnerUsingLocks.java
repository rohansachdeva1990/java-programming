package com.rohan.java.concurrency.concepts.deadlocks;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by rohan on 18/03/2017.
 *
 * Base
 */
public class RunnerUsingLocks {

    private Account acc1 = new Account();
    private Account acc2 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    public void firstThread() throws InterruptedException {
        lock1.lock();
        lock2.lock();
        try{
            Random random = new Random();
            for(int i =0; i < 10000; i++){
                Account.transfer(acc1, acc2, random.nextInt(100));
            }
        }
        finally{
            lock1.unlock();
            lock2.unlock();
        }
    }

    public void secondThread() throws InterruptedException {
        lock1.lock();
        lock2.lock();
        try{
            Random random = new Random();
            for(int i =0; i < 10000; i++){
                Account.transfer(acc2, acc1, random.nextInt(100));
            }
        }
        finally{
            lock1.unlock();
            lock2.unlock();
        }
    }

    public void finished() {
        System.out.println("Account 1 balance: " + acc1.getBalance());
        System.out.println("Account 2 balance: " + acc2.getBalance());
        System.out.println("Total balance: " + (acc1.getBalance() + acc2.getBalance()));
    }
}
