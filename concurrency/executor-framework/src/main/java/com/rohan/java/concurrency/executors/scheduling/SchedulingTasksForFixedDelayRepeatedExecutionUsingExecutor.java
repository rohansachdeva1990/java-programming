package com.rohan.java.concurrency.executors.scheduling;

import com.rohan.concurrency.common.Utils;
import com.rohan.concurrency.common.factory.NamedThreadFactory;
import com.rohan.concurrency.common.tasks.ScheduledTaskB;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class SchedulingTasksForFixedDelayRepeatedExecutionUsingExecutor {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");

    public static void main(String[] args) throws Exception {

        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory());
        System.out.println("[" + currentThreadName + "] Current time: " + dateFormat.format(new Date()));
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(new ScheduledTaskB(1000), 4, 2, TimeUnit.SECONDS);

         for (int i = 0; i < 10; i++) {
            System.out.println("[" + currentThreadName + "] Next run of task scheduled at approx.");
            Date scheduledTime = Utils.getFutureTime(new Date(), scheduledFuture.getDelay(TimeUnit.MILLISECONDS));
            System.out.println(dateFormat.format(scheduledTime));

            TimeUnit.MILLISECONDS.sleep(3000);
        }

        /**
         * If you have scheduled repeated executions only on the schedule executor service then it is going
         * to terminate immediately when it's shutdown method is invoked.
         */
        //TimeUnit.MILLISECONDS.sleep(15000);
        scheduledExecutorService.shutdown();
        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}
