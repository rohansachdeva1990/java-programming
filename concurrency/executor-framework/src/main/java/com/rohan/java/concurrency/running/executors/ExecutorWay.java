package com.rohan.java.concurrency.running.executors;

/**
 * They can be re-used.
 *
 * Each thread in the pool executes multiple tasks one-by-one, as long as the queue is empty.
 *
 * On shutdown, executor task will not accept any new tasks.
 *
 * explore more about shutdownNow()
 *
 * When we want our task to return a value, we use Callable instead of runnable
 *
 * An exception, when submitting new task to a executor service which is already closed (being shutdown-ed)
 *
 * shutdownNow(), stops all the actively running tasks and also halt the processing of all the waiting task.
 *
 * we can use isShutdown()
 *
 * Executors is a factory class, providing different implementations of executor.
 *
 * Prefer cached thread pool for short lived asynchronous tasks.
 */
public class ExecutorWay {

    public static void main(String[] args) {

    }

}
