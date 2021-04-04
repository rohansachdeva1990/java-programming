package com.rohan.java.concurrency.executors.returnval;

import com.rohan.concurrency.common.tasks.CalcTaskA;
import com.rohan.concurrency.common.factory.NamedThreadFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Task 2 and Task 3 were printed only after task 1 completion. It will be in sequence
 */
public class ReturningValueUsingExecutorFirstWay {

    public static void main(String[] args) {

        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        ExecutorService executorService = Executors.newCachedThreadPool(new NamedThreadFactory());

        Future<Integer> result1 = executorService.submit(new CalcTaskA(1, 2, 2000));
        Future<Integer> result2 = executorService.submit(new CalcTaskA(3, 4, 1000));
        Future<Integer> result3 = executorService.submit(new CalcTaskA(5, 6, 500));

        executorService.shutdown();

        // How to know, all the task is finished.
        try {
            System.out.println("Result 1 : " + result1.get());
            System.out.println("Result 2 : " + result2.get());
            System.out.println("Result 3 : " + result3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }

}
