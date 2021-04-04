package com.rohan.java.concurrency.threads.running;

import java.util.concurrent.TimeUnit;

public class FirstWayThread {

    public static void main(String[] args) {

        // We don't know which thread is going to get the cpu
        System.out.println("Main thread start here..");
        new SecondTask();
        Thread t = new SecondTask();
        System.out.println("Main thread ends here..");
    }
}

class FirstTask extends Thread {

    private static int count  = 0;
    private int id;

    public FirstTask() {
        this.id = ++count;
        this.start(); // In order to start the thread
    }

    public void run() {
        for(int i = 0; i < 10; i++){
            System.out.println("<" + id + ">" + " TICK TICK " + i);
            delay();
        }
    }

    private void delay(){
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
