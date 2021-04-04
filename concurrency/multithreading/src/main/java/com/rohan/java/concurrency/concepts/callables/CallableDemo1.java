package com.rohan.java.concurrency.concepts.callables;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by rohan on 18/03/2017.
 * <p>
 * To demonstrates how we can get return from our threads
 * <p>
 * 1. Implement a Runnable and store the result in one of the instance variable
 * 2. Use callable and futures
 */
public class CallableDemo1 {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        /*
        executorService.submit(()->{
            Random random = new Random();
            int duration = random.nextInt(4000);
            System.out.println("Starting..");

            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Finished");
        });
*/

        Future<Integer> integerFuture = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                int duration = random.nextInt(4000);

                // How to catch exception
                if(duration > 2000){
                    throw new IOException("sleeping for too long");
                }

                System.out.println("Starting..");

                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Finished");
                return duration;
            }
        });

        executorService.shutdown();


/*
    Use for multiple threads scenario

        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/
        // Here Get is blocking, so it will wait for executor service to complete
        try {
            System.out.println("Result is " + integerFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            // To retrieve exec
            IOException ex = (IOException) e.getCause();
            // When an exception is thrown from the the call() methods when
            System.out.println(ex.getMessage());

        }
    }
}
