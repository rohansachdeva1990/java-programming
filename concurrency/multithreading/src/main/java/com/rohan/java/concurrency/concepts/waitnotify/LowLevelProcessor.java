package com.rohan.java.concurrency.concepts.waitnotify;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by rosachde on 3/17/2017.
 *
 * A thread can also wake up without being notified, interrupted, or timing out, a so-called spurious wakeup.
 * While this will rarely occur in practice, applications must guard against it by testing for the condition
 * that should have caused the thread to be awakened, and continuing to wait if the condition is not satisfied.
 * In other words, waits should always occur in loops
 *
 * With the above code, there may be 2 consumer threads. When the producer locks the queue to add to
 * it, consumer #1 may be at the synchronized lock read to go in while consumer #2 is already waiting.
 * When the item is added to the queue and notify called, #2 is notified and moves to the run queue
 * but it is behind the #1 consumer waiting for the queue lock. Since the #1 consumer goes forward
 * first to remove from the queue, an exception would occur if the while loop is just an if
 * because #2 will try to call remove as well. Just because it was notified, it needs to be
 * make sure the queue is still empty because of the race condition.
 *
 * There might be more then just one worker waiting for a condition to become true.
 *
 * If two or more worker get awake (notifyAll) they have to check the condition again,
 * otherwise all workers would continue even though there might only be data for one of them.
 *
 */
public class LowLevelProcessor {
    private LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;
    private Object lock = new Object();

    public void produce() throws InterruptedException{
        int value = 0;
        while(true) {
            synchronized (lock) {
                // Only add if and only if list is not full
                while(list.size() == LIMIT){
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException{
        Random random = new Random();


        while (true) {
            synchronized (lock){

                while (list.size() == 0){
                    lock.wait();
                }

                System.out.print("List size is: " + list.size());
                int value = list.removeFirst();
                System.out.println(" ; Value is: " +  value);
                lock.notify();
            }

            Thread.sleep(random.nextInt(1000));
        }
    }
}
