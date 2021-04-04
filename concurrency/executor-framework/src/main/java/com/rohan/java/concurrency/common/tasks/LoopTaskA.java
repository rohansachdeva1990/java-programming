package com.rohan.java.concurrency.common.tasks;

import java.util.concurrent.TimeUnit;

public class LoopTaskA implements Runnable {

    private static int count = 0;
    private int id;

    public LoopTaskA() {
        this.id = ++count;
    }

    public void run() {

        System.out.println("###### <TASK-" + id + "> STARTING ######");

        for (int i = 0; i < 10; i++) {
            System.out.println("<TASK-" + id + ">" + " TICK TICK " + i);
            try{
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
            }
            catch (InterruptedException e){
                e.printStackTrace();
                break;
            }
        }

        System.out.println("###### <TASK-" + id + "> DONE ######");
    }
}