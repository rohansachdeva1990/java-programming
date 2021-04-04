package com.rohan.java.concurrency.concepts.threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by rohan on 17/03/2017.
 *
 *
 * There is lot of overhead when we create thread, to overcome this issue we use thread pool
 * to recycle threads
 */

class Processor implements Runnable{

    private  int id;

    Processor(int id){
        this.id = id;
    }
    @Override
    public void run() {
        System.out.println("Starting : " + id);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Ending : " + id);
    }
}

public class ThreadPoolDemo {

    public static void main(String[] args) {

        // Thread pool is like having number of workers in a factory, at a time 2 threads
        // will be running, and we can give our factory workers list of these tasks
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for(int i = 0; i < 5 ; i++){
            executorService.submit(new Processor(i));
        }

        // Now stop accepting new task and shutdown when all task are complete
        executorService.shutdown(); // Will wait for all threads to complete

        System.out.println("All task submitted");

        // If we want main should be the last thread to exit, we can add
        try {

            // This one will wait till..... completed
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All task completed");
    }

}
