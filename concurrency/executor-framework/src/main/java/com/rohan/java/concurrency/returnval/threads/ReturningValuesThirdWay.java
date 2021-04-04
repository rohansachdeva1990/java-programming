package com.rohan.java.concurrency.returnval.threads;


import com.rohan.java.concurrency.common.tasks.ValueReturningTaskC;

public class ReturningValuesThirdWay {
    public static void main(String[] args) throws InterruptedException {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        // Q: Try playing with the timings... eg 2000, 1000, 500
        ValueReturningTaskC task1 = new ValueReturningTaskC(1,2,2000);
        ValueReturningTaskC task2 = new ValueReturningTaskC(3,4,1000);
        ValueReturningTaskC task3 = new ValueReturningTaskC(5,6,500);

        Thread t1 = new Thread(task1, "Thread - 1");
        Thread t2 = new Thread(task2, "Thread - 2");
        Thread t3 = new Thread(task3, "Thread - 3");

        t1.start();
        t2.start();
        t3.start();

        // We need to a way to wait before the task is finished.
        t1.join();
        System.out.println("Result-1: " + task1.getSum());

        t2.join();
        System.out.println("Result-2: " + task2.getSum());

        t3.join();
        System.out.println("Result-3: " + task3.getSum());

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}
