package com.rohan.java.concurrency.returnval.executors;

import com.rohan.concurrency.common.tasks.CalcTaskA;
import com.rohan.concurrency.common.factory.NamedThreadFactory;

import java.util.concurrent.*;

/**
 *
 */
public class ReturningValueUsingExecutorSecondWay {

    public static void main(String[] args) {

        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        ExecutorService executorService = Executors.newCachedThreadPool(new NamedThreadFactory());

        // We are expecting integer type as return values
        CompletionService<Integer> tasks = new ExecutorCompletionService<Integer>(executorService);

        tasks.submit(new CalcTaskA(1, 2, 2000));
        tasks.submit(new CalcTaskA(3, 4, 1000));
        tasks.submit(new CalcTaskA(5, 6, 500));

        // We can use runnanble also
        tasks.submit(() -> {
            System.out.println("Hello from runnable thread");
        }, 999);


        executorService.shutdown();


        for (int i = 0; i < 4; i++) {
            try {
                System.out.println("Result : " + tasks.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }

}
