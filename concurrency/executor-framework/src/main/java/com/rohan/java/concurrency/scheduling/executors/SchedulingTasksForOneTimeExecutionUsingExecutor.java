package com.rohan.java.concurrency.scheduling.executors;

import com.rohan.java.concurrency.common.factory.NamedThreadFactory;
import com.rohan.java.concurrency.common.tasks.CalcTaskD;
import com.rohan.java.concurrency.common.tasks.ScheduledTaskB;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class SchedulingTasksForOneTimeExecutionUsingExecutor {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");

    public static void main(String[] args) throws Exception {

        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        //ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory());
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3, new NamedThreadFactory());

        System.out.println("[" + currentThreadName + "] Current time : " + dateFormat.format(new Date()));

        ScheduledFuture<?> scheduledFuture1 = scheduledExecutorService.schedule(new ScheduledTaskB(3000),
                4, TimeUnit.SECONDS);

        ScheduledFuture<Integer> scheduledFuture2 = scheduledExecutorService.schedule(new CalcTaskD(2, 3, 3000),
                6, TimeUnit.SECONDS);

        scheduledExecutorService.schedule(new ScheduledTaskB(0), 8, TimeUnit.SECONDS);

        ScheduledFuture<Integer> scheduledFuture4 = scheduledExecutorService.schedule(new CalcTaskD(3, 4, 0),
                10, TimeUnit.SECONDS);

        // How to cancel - If running and we try to fetch the result - we may get cancellation exception
        scheduledFuture1.cancel(true);
        scheduledFuture2.cancel(true);

        scheduledExecutorService.shutdown();

        System.out.println("[" + currentThreadName + "] RETRIEVING THE RESULT NOW... \n");

/*
        Just trying to see what happens if we avoid fetching the cancelled tasks
        System.out.println("[" + currentThreadName + "] TASK-1 RESULT: " + scheduledFuture1.get() + "\n");
        System.out.println("[" + currentThreadName + "] TASK-2 RESULT: " + scheduledFuture2.get() + "\n");*/
        System.out.println("[" + currentThreadName + "] TASK-4 RESULT: " + scheduledFuture4.get() + "\n");

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}
