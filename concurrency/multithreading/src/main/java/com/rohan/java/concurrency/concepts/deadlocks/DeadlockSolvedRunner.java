package com.rohan.java.concurrency.concepts.deadlocks;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by rohan on 18/03/2017.
 *
 * Some ways to solve deadlock
 *
 *  1. Reordering the locks, it should be in the same order of their acquisition
 *  2. Or use try lock and handle it, to get rid of order problem
 *
 */
public class DeadlockSolvedRunner {

    private Account acc1 = new Account();
    private Account acc2 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    public static void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {

        boolean gotFirstLock = false;
        boolean gotSecondLock = false;

        while(true) {
            try {
                gotFirstLock = firstLock.tryLock();
                gotSecondLock = secondLock.tryLock();
            } finally {

                // If both lock are acquired
                if(gotFirstLock && gotSecondLock){
                    return;
                }

                // So the thread which acquired the other lock get chance
                if(gotFirstLock){
                    firstLock.unlock();
                }

                if(gotSecondLock){
                    secondLock.unlock();
                }
            }
            Thread.sleep(1);
        }
    }



    public void firstThread() throws InterruptedException {

        acquireLocks(lock1, lock2);
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

    /*
        We have reversed the order of the locks -> Now we have a deadlock state
     */
    public void secondThread() throws InterruptedException {

        acquireLocks(lock2, lock1);
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
