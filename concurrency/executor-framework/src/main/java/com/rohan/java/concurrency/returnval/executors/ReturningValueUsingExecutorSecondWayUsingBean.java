package com.rohan.java.concurrency.returnval.executors;

import com.rohan.concurrency.common.tasks.CalcTaskB;
import com.rohan.concurrency.common.factory.NamedThreadFactory;

import java.util.concurrent.*;

/**
 * Task 2 and Task 3 were printed only after task 1 completion. It will be in sequence
 */
public class ReturningValueUsingExecutorSecondWayUsingBean {

    public static void main(String[] args) {

        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        ExecutorService executorService = Executors.newCachedThreadPool(new NamedThreadFactory());

        // We are expecting integer type as return values
        CompletionService<TaskResult<String, Integer>> tasks = new ExecutorCompletionService<TaskResult<String, Integer>>(executorService);

        tasks.submit(new CalcTaskB(1, 2, 2000));
        tasks.submit(new CalcTaskB(3, 4, 1000));
        tasks.submit(new CalcTaskB(5, 6, 500));

        // We can use runnable also
        tasks.submit(() -> {
            System.out.println("Hello from runnable thread");
        }, new TaskResult<String, Integer>("MyTask", 989));


        executorService.shutdown();

        for(int i = 0; i < 4; i++){
            try {
                System.out.println("Result: " + tasks.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("[" + currentThreadName + "] Main thread ends here...");

    }
}
