package com.rohan.java.concurrency.concepts.semaphores;

import java.util.concurrent.Semaphore;

/**
 * Created by rohan on 18/03/2017.
 *
 * Main intention to use finally block is that we always release the resources that we acquire on demand
 */
public class Connection {
    public static Connection instance = new Connection();

    private int connectionsCount = 0;

    //private Semaphore semaphore = new Semaphore(10);
    // "fair" means that we want the  first thread to get the chance which was blocked when sem was not available
    private Semaphore semaphore = new Semaphore(10, true);

    private Connection(){
    }

    public static Connection getInstance(){
        return instance;
    }

    public void connect() throws InterruptedException {
        try {
            semaphore.acquire();
            doConnect();
        }
        finally {
            semaphore.release();
        }
    }

    public void doConnect() throws InterruptedException {
        synchronized (this){
            connectionsCount++;
            System.out.println("Current connections: " + connectionsCount);
        }

        Thread.sleep(1000);

        synchronized (this) {
            connectionsCount--;
        }
    }
}
