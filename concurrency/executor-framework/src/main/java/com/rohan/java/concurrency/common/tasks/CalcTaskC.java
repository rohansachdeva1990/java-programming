package com.rohan.java.concurrency.common.tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class CalcTaskC implements Callable<Long> {

    private static int count = 0;
    private int instanceNumber;
    private String taskId;

    private final int DATA_SET_SIZE = 100000;


    private boolean isThreadInterrupted = false;


    public CalcTaskC() {
        this.instanceNumber = ++count;
        this.taskId = "CalcTaskC-" + instanceNumber;
    }

    /**
     * This method will return average time for the operation
     * @return
     * @throws Exception
     */
    @Override
    public Long call() throws Exception {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> STARTING ######");

        long totalTimeTakenInMs = 0;

        // Simulating long running task...
        for (int i = 0; i < 1000; i++) {

            System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> CURRENT RUNNING AVERAGE = "
                    + (i == 0 ? 0 : totalTimeTakenInMs / (2 * i)));

            long timeTakenInMs = doSomeWork();
            totalTimeTakenInMs += timeTakenInMs;

            if (Thread.interrupted()) {
                System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> Interrupted, Cancelling ....");
                isThreadInterrupted = true;
                break;
            }
        }

        System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> Retrieving 'INTERRUPTED' status again: " + Thread.interrupted());
        System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> DONE ######");

        return isThreadInterrupted? - 1 : totalTimeTakenInMs / (2 * 1000);
    }


    private long doSomeWork() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 2; i++) {
            Collections.sort(generateDataSet());
        }

        return System.currentTimeMillis() - startTime;

    }

    private List<Integer> generateDataSet() {

        List<Integer> integerList = new ArrayList<>();
        Random randomGenerator = new Random();
        for (int i = 0; i < DATA_SET_SIZE; i++) {
            integerList.add(randomGenerator.nextInt(DATA_SET_SIZE));
        }
        return integerList;
    }

}