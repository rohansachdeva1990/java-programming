package com.rohan.java.concurrency.scheduling.executors;


import com.rohan.java.concurrency.common.factory.NamedThreadFactory;
import com.rohan.java.concurrency.common.tasks.ScheduledTaskB;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


/**
 * To cancel the task:-
 *  - Use shutdown() to cancel all before waiting.
 *  or cancel();
 */
public class SchedulingTasksForFixedDelayRepeatedExecutionUsingExecutorMultipleTask {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");

    public static void main(String[] args) throws Exception {

        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        // For multiple tasks, it will drift the delay forward for the subsequent executions of the corresp. task
        //ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory());

        // Due to availability of multiple threads each task starts on time.
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3, new NamedThreadFactory());

        System.out.println("[" + currentThreadName + "] Current time: " + dateFormat.format(new Date()));
        ScheduledFuture<?> scheduledFuture1 = scheduledExecutorService.scheduleWithFixedDelay(new ScheduledTaskB(1000), 4, 2, TimeUnit.SECONDS);
        ScheduledFuture<?> scheduledFuture2 = scheduledExecutorService.scheduleWithFixedDelay(new ScheduledTaskB(3000), 4, 2, TimeUnit.SECONDS);

        scheduledFuture2.cancel(true);

        TimeUnit.MILLISECONDS.sleep(15000);
        scheduledExecutorService.shutdown();

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}
