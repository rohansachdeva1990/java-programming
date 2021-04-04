package com.rohan.java.concurrency.common.factory;

import com.rohan.concurrency.common.handlers.ThreadExceptionHandler;

public class ThreadFactoryWithExceptionHandler extends NamedThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread t = super.newThread(r);
        t.setUncaughtExceptionHandler(new ThreadExceptionHandler());
        return t;
    }
}
