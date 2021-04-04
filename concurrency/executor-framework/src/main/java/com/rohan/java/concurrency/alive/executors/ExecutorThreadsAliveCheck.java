package com.rohan.java.concurrency.alive.executors;


import com.rohan.java.concurrency.common.Utils;
import com.rohan.java.concurrency.common.factory.NamedThreadFactory;
import com.rohan.java.concurrency.common.tasks.CalcTaskA;
import com.rohan.java.concurrency.common.tasks.LoopTaskC;

import java.util.concurrent.*;

// Run loopTask c with 500 millis
public class ExecutorThreadsAliveCheck {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");


        ExecutorService executorService = Executors.newCachedThreadPool(new NamedThreadFactory());

        // For runnable, as it does not return any value, we are not interested
        Future<?> f1 = executorService.submit(new LoopTaskC());
        Future<Integer> f2 = executorService.submit(new CalcTaskA(3, 4, 700));

        // Runnable examples
        FutureTask<?> ft3 = new FutureTask<Void>(new LoopTaskC(), null);
        FutureTask<Integer> ft4 = new FutureTask<Integer>(new LoopTaskC(), 498);

        // Callable
        FutureTask<Integer> ft5 = new FutureTask<Integer>(new CalcTaskA(4, 5, 500));

        executorService.execute(ft3);
        executorService.execute(ft4);
        executorService.execute(ft5);

        executorService.shutdown();

        // Checking is alive

        // Play with this iteration also
        for (int i = 1; i <= 10; i++) {
            Utils.sleepInMillis(600);
            System.out.println("[" + currentThreadName + "] ITR - " + i + " -> LoopTaskC - 1 done = " + f1.isDone());
            System.out.println("[" + currentThreadName + "] ITR - " + i + " -> CalcTaskA - 1 done = " + f2.isDone());
            System.out.println("[" + currentThreadName + "] ITR - " + i + " -> LoopTackC - 2 done = " + ft3.isDone());
            System.out.println("[" + currentThreadName + "] ITR - " + i + " -> LoopTackC - 3 done = " + ft4.isDone());
            System.out.println("[" + currentThreadName + "] ITR - " + i + " -> CalcTaskA - 2 done = " + ft5.isDone());
        }

        System.out.println("\n$$$$ [" + currentThreadName + "] Retrieving results now .... $$$$");

        System.out.println("[" + currentThreadName + "] 'LoopTaskC-1' result: " + f1.get());
        System.out.println("[" + currentThreadName + "] 'CalcTaskA-1' result: " + f2.get());
        System.out.println("[" + currentThreadName + "] 'LoopTaskC-2' result: " + ft3.get());
        System.out.println("[" + currentThreadName + "] 'LoopTaskC-3' result: " + ft4.get());
        System.out.println("[" + currentThreadName + "] 'CalcTaskA-2' result: " + ft5.get());

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}
