package com.rohan.java.concurrency.common.tasks;

import com.rohan.concurrency.common.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

public class CalcTaskD implements Callable<Integer> {

    private int a;
    private int b;
    private long sleeptTime;

    private static int count = 0;
    private int instanceNumber;
    private String taskId;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
    public CalcTaskD(int a, int b, long sleeptTime) {
        this.a = a;
        this.b = b;
        this.sleeptTime = sleeptTime;

        this.instanceNumber = ++count;
        this.taskId = "CalcTaskD-" + instanceNumber;
    }

    public Integer call() throws Exception {
        Date startTime = new Date();
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("###### [" + currentThreadName + "] <" + taskId + "> STARTED AT "
                + dateFormat.format(startTime) + " ######");
        System.out.println("[" + currentThreadName + "] <" + taskId + "> Sleeping for " + sleeptTime + " millis");

        Utils.sleepInMillis(sleeptTime);

        System.out.println("###### [" + currentThreadName + "] <" + taskId + "> FINISHED AT "
                + dateFormat.format(new Date()) + " ******\n");

        return a + b;
    }
}
