package com.rohan.java.concurrency.common.tasks;

import com.rohan.concurrency.common.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduledTaskB implements Runnable {

    private long timeout;
    private static int count = 0;
    private int instanceNumber;
    private String taskId;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");

    public ScheduledTaskB(long timeout) {
        this.timeout = timeout;
        this.instanceNumber = ++count;
        this.taskId = "ScheduledTaskB-" + instanceNumber;
    }

    public void run() {
        Date startTime = new Date();

        String currentThreadName = Thread.currentThread().getName();
        System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> STARTED AT: " +
                dateFormat.format(startTime) + " #####");

        Utils.sleepInMillis(timeout);

        System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> FINISHED AT: "
                + dateFormat.format(new Date()) + " #####\n");
    }
}
