package com.rohan.java.concurrency.threads.returnval;


import com.rohan.java.concurrency.common.handlers.ResultListener;

public class SumObserver implements ResultListener<Integer> {

    private String taskId;

    public SumObserver(String taskId){
        this.taskId = taskId;
    }

    public void notifyResult(Integer result) {
        System.out.println("Result for " + taskId + " = " + result);
    }
}
