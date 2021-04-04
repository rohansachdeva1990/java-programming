package com.rohan.java.concurrency.common.tasks;


import com.rohan.java.concurrency.common.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class ScheduledTaskA extends TimerTask {

    private long timeout;
    private static int count = 0;
    private int instanceNumber;
    private String taskId;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");

    public ScheduledTaskA(long timeout) {
        this.timeout = timeout;
        this.instanceNumber = ++count;
        this.taskId = "ScheduledTaskA-" + instanceNumber;
    }

    public void run() {
        Date startTime = new Date();

        // Getting the scheduled start time of the task
        Date scheduledForRunning = new Date(super.scheduledExecutionTime());

        String currentThreadName = Thread.currentThread().getName();
        System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> SCHEDULED TO RUN AT: "
                + dateFormat.format(scheduledForRunning) + ", ACTUALLY STARTED AT: " +
                dateFormat.format(startTime) + " #####");

        Utils.sleepInMillis(timeout);

        System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> FINISHED AT: "
                + dateFormat.format(new Date()) + " #####\n");
    }
}
