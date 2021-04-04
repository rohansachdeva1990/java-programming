package com.rohan.java.concurrency.common.factory;


import com.rohan.java.concurrency.common.handlers.ThreadExceptionHandler;

public class ThreadFactoryWithExceptionHandlerAlternator extends NamedThreadFactory {

    public int count = 0;

    @Override
    public Thread newThread(Runnable r) {
        Thread t = super.newThread(r);

        int sequenceNumber = ++count;
        if(sequenceNumber % 2 == 0){
            t.setUncaughtExceptionHandler(new ThreadExceptionHandler());
        }

        return  t;
    }
}
