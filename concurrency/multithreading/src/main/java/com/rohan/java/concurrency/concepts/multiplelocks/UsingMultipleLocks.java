package com.rohan.java.concurrency.concepts.multiplelocks;

/**
 * Created by rohan on 16/03/2017.
 */



public class UsingMultipleLocks {
    public static void main(String[] args) {
        //new NonSyncWorker().main();

        new SyncBlockWorker().main();
    }
}
