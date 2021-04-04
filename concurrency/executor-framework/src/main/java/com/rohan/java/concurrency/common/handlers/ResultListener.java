package com.rohan.java.concurrency.common.handlers;

public interface ResultListener<T> {

    void notifyResult(T result);
}
