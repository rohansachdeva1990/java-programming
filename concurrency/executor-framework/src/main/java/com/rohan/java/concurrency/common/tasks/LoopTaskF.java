package com.rohan.java.concurrency.common.tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LoopTaskF implements Runnable {

    private static int count = 0;
    private int instanceNumber;
    private String taskId;

    private final int DATA_SET_SIZE = 100000;

    public LoopTaskF() {
        this.instanceNumber = ++count;
        this.taskId = "loopTaskF-" + instanceNumber;
    }

    public void run() {
        try {
            String currentThreadName = Thread.currentThread().getName();
            System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> STARTING ######");

            // Simulating long running task...
            for (int i = 0; ; i++) {

                doSomeWork();
                if (Thread.interrupted()) {
                    System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> Interrupted, Cancelling ....");
                    break;
                }
            }

            System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> Retrieving 'INTERRUPTED' status again: " + Thread.interrupted());
            System.out.println("###### [" + currentThreadName + "] <TASK-" + taskId + "> DONE ######");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    private void doSomeWork() {
        for (int i = 0; i < 2; i++) {
            Collections.sort(generateDataSet());
        }
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