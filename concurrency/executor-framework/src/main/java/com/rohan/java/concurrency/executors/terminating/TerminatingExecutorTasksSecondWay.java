package com.rohan.java.concurrency.executors.terminating;

import com.rohan.concurrency.common.tasks.CalcTaskC;
import com.rohan.concurrency.common.tasks.LoopTaskF;
import com.rohan.concurrency.common.factory.NamedThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TerminatingExecutorTasksSecondWay {

    public static void main(String[] args) throws InterruptedException {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        //ExecutorService executorService = Executors.newCachedThreadPool(new NamedThreadFactory());
        ExecutorService executorService = Executors.newFixedThreadPool(2, new NamedThreadFactory());

        // callable
        CalcTaskC task1 = new CalcTaskC();

        // runnable
        LoopTaskF task2 = new LoopTaskF();
        LoopTaskF task3 = new LoopTaskF();

        Future<Long> f1 = executorService.submit(task1);
        Future<?> f2 = executorService.submit(task2);
        Future<?> f3 = executorService.submit(task3);

        executorService.shutdown();

        TimeUnit.MILLISECONDS.sleep(4000);

        System.out.println("[" + currentThreadName + "] Interrupting 'CalcTaskC-1...");
        System.out.println("[" + currentThreadName + "] Cancelling f1 = " + f1.cancel(true));


        System.out.println("[" + currentThreadName + "] Interrupting 'LoopTaskF-1...");
        System.out.println("[" + currentThreadName + "] Cancelling f2 = " + f2.cancel(true));

        //TimeUnit.MILLISECONDS.sleep(4000);

        System.out.println("[" + currentThreadName + "] Interrupting 'LoopTaskF-2...");
        System.out.println("[" + currentThreadName + "] Cancelling f3 = " + f3.cancel(false));


        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}
