package com.rohan.java.concurrency.common.factory;

import java.util.concurrent.ThreadFactory;

public class NamedThreadFactory implements ThreadFactory {

    private static int count = 0;

    public Thread newThread(Runnable r) {
        return new Thread(r,"MyThread-" + ++count);
    }
}
