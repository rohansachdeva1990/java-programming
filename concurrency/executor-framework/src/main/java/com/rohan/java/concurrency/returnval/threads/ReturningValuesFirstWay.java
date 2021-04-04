package com.rohan.java.concurrency.returnval.threads;


import com.rohan.java.concurrency.common.tasks.ValueReturningTaskA;

/**
 * For normal threads, how to return value from the task executed by the thread.
 *
 * Here, the tasks are finishing on the basis of waiting period. Main thread will stop, if the worker thread is not
 * finished executing the task. So, if task 3 is finished before task 2. The output will still be delayed due to
 * task 2.
 */
public class ReturningValuesFirstWay {

    public static void main(String[] args) {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        // Q: Try playing with the timings... eg 2000, 1000, 500
        ValueReturningTaskA taskA1 = new ValueReturningTaskA(1,2,100);
        ValueReturningTaskA taskA2 = new ValueReturningTaskA(3,4,1000);
        ValueReturningTaskA taskA3 = new ValueReturningTaskA(5,6,500);

        Thread t1 = new Thread(taskA1, "Thread - 1");
        Thread t2 = new Thread(taskA2, "Thread - 2");
        Thread t3 = new Thread(taskA3, "Thread - 3");

        t1.start();
        t2.start();
        t3.start();

        // We need to a way to wait before the task is finished.

        System.out.println("Result-1: " + taskA1.getSum());
        System.out.println("Result-2: " + taskA2.getSum());
        System.out.println("Result-3: " + taskA3.getSum());


        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}
