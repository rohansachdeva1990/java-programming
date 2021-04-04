package com.rohan.java.concurrency.common.handlers;

public class ThreadExceptionHandler implements Thread.UncaughtExceptionHandler {

    private String handlerId;

    // Is it mandatory? Need to check
    public ThreadExceptionHandler() { }

    public ThreadExceptionHandler(String handlerId){
        this.handlerId = handlerId;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(this + " caught exception in thread. \"" + t.getName() + "\" => " + e);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "@" + this.hashCode()
                + (( handlerId == null || "".equals(handlerId)) ? "" : "(\"" + handlerId + "\")");
    }
}
