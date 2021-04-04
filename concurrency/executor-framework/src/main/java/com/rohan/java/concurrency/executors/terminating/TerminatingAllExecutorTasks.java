package com.rohan.java.concurrency.executors.terminating;

import com.rohan.concurrency.common.factory.NamedThreadFactory;
import com.rohan.concurrency.common.tasks.*;
import com.rohan.concurrency.executors.returnval.TaskResult;

import java.util.concurrent.*;

/**
 * This class will show case every type of task - runnable/callable with block/non-block nature
 */
public class TerminatingAllExecutorTasks {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        ExecutorService executorService = Executors.newCachedThreadPool(new NamedThreadFactory());

        // block runnable
        LoopTaskA task1 = new LoopTaskA();

        // non block runnable
        LoopTaskF task2 = new LoopTaskF();

        // non block callable
        CalcTaskC task4 = new CalcTaskC();

        // block callable
        FactorialTaskB task3 = new FactorialTaskB(30, 500);
        CalcTaskB task5 = new CalcTaskB(2, 3, 9000); // does not handle interrupts - shutdown will now be interrupted

        executorService.execute(task1);
        executorService.execute(task2);
        Future<Long> t3Future = executorService.submit(task3);
        Future<Long> t4Future = executorService.submit(task4);
        Future<TaskResult<String, Integer>> t5Future = executorService.submit(task5);

        TimeUnit.MILLISECONDS.sleep(1000);

        executorService.shutdownNow();
        System.out.println("[" + currentThreadName + "] All threads terminated = " + executorService.awaitTermination(5000, TimeUnit.MILLISECONDS));

        System.out.println("[" + currentThreadName + "] 'FactorialTaskB-1' Result = " + t3Future.get());
        System.out.println("[" + currentThreadName + "] 'CalcTaskC-1' Result = " + t4Future.get());

        // It will throw exception; because sleep was interrupted, but due to callable throwing an exception it was somewhat
        // stored by the executor framework. But when we called get() on Future tasks. We got an ExecutionException.
        try{
            System.out.print("[" + currentThreadName + "] 'CalcTaskB-1' Result = " + t5Future.get());
        }
        catch (ExecutionException e){
            System.out.println("------ [" + currentThreadName + "] CalcTaskB got execution exception. The cause is :" );
            e.getCause().printStackTrace();
        }

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}
