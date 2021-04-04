package com.rohan.java.concurrency.threads.returnval;


import com.rohan.java.concurrency.common.tasks.ValueReturningTaskB;

/**
 * Use of Observer pattern to display the result as soon as possible for the task which has finished executing earlier.
 *
 * The one finished, will use the callback functionality.
 */
public class ReturningValuesSecondWay {

    public static void main(String[] args) {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        ValueReturningTaskB taskA1 = new ValueReturningTaskB(1,2,2000, new SumObserver("Task-1"));
        ValueReturningTaskB taskA2 = new ValueReturningTaskB(3,4,1000, new SumObserver("Task-2"));
        ValueReturningTaskB taskA3 = new ValueReturningTaskB(5,6,500, new SumObserver("Task-3"));

        Thread t1 = new Thread(taskA1, "Thread - 1");
        Thread t2 = new Thread(taskA2, "Thread - 2");
        Thread t3 = new Thread(taskA3, "Thread - 3");

        t1.start();
        t2.start();
        t3.start();

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}
