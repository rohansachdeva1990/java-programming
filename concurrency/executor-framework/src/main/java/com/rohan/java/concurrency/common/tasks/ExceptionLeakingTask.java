package com.rohan.java.concurrency.common.tasks;

public class ExceptionLeakingTask implements Runnable {

    @Override
    public void run() {

        // Why runtime exceptions? Not checked exceptions?
        // - because checked exception is not declared by the runnable method signature.
        // Thats why we are forced to handle all the checked exception in the run method
        // Try to relate it to the overriding concept

        // This runtime exception can be thrown by some third party classes.
        throw new RuntimeException();
    }
}
