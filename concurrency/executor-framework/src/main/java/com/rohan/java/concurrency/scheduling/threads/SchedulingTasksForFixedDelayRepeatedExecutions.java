package com.rohan.java.concurrency.scheduling.threads;

import com.rohan.concurrency.common.Utils;
import com.rohan.concurrency.common.tasks.ScheduledTaskA;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class SchedulingTasksForFixedDelayRepeatedExecutions {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");

    public static void main(String[] args) throws InterruptedException {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        Timer timer = new Timer("Timer-Thread", true);
        Date currentTime = new Date();

        System.out.println("[" + currentThreadName + "] Current time: " + dateFormat.format(currentTime));

        Date scheduleTime = Utils.getFutureTime(currentTime, 3000);
        long intervalMillis = 2000;

        timer.schedule(new ScheduledTaskA(1000), scheduleTime, intervalMillis);
        System.out.println("[" + currentThreadName + "] Task-1 first run scheduled for " + dateFormat.format(scheduleTime) +
                " and then repeatedly at an interval of every " + intervalMillis / 1000 + " seconds!");

        long delayMillis = 4000;
        long intervalMillis2 = 2000;

        timer.schedule(new ScheduledTaskA(1000), delayMillis, intervalMillis2);
        System.out.println("[" + currentThreadName + "] Task-2 first run scheduled " + delayMillis / 1000 + " seconds after "
         + dateFormat.format(currentTime) + " and then repeatedly at an interval of every " + intervalMillis2 / 1000  + " seconds!");

        TimeUnit.MILLISECONDS.sleep(16000);
        System.out.println("[" + currentThreadName + "] Cancelling the timer now ...");
        timer.cancel();

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}