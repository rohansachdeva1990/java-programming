package com.rohan.java.concurrency.common.factory;

public class DaemonThreadFactory extends NamedThreadFactory {

    private static int count = 0;

    @Override
    public Thread newThread(Runnable r) {
        Thread t = super.newThread(r);
        setDaemon(t);
        return t;
    }

    private void setDaemon(Thread t){
        count++;
        if(count % 2 == 0){
            t.setDaemon(true);
        }
    }
}

