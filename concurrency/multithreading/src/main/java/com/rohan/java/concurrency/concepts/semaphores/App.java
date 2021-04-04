package com.rohan.java.concurrency.concepts.semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The most important difference between a cached thread pool and a fixed thread pool in Java is that
 * the cached thread pool has no upper limit on the number of threads it will spawn and use. Which one
 * is preferred depends on what you want the scaling behavior to be like.
 *
 * The main advantage of the cached thread pool is that threads will begin execution immediately even if
 * you have an unanticipated large number of tasks to execute. For example, your business needs may increase
 * over months or years, and your application may be moved to more powerful machines, and use of a
 * cached thread pool will permit servicing the increased needs by using the increased available processing
 * power without having to change the code. This can be an advantage since once the application has been
 * in service for months or years, people may not remember the code well enough easily to identify the
 * thread limit as a parameter that can be changed to improve performance.
 *
 * The main advantage of the fixed thread pool is that the number of threads is more tightly controlled.
 * This helps prevent other parts of the software installation - either within the application or in other
 * applications - from being starved of processing power should this application receive a large number of
 * tasks in a short period of time. In addition, the risk of running into the operating system threading limit
 * is reduced, reducing the risks of software crashes that can result when a process needs to spawn a thread
 * and is not able to do so.
 *
 * It doesn’t put tasks into a queue. Consider this as the same as using a queue with the maximum size of 0.
 * When all current threads are busy, it creates another thread to run the task. Sometimes it can
 * reuse threads. Good for denial of service attacks on your own servers. The problem with a cached
 * thread pool is that it doesn’t know when to stop spawning more and more threads. Imagine the situation
 * where you have computationally intensive tasks that you submit into this executor. The more threads
 * that consuming the CPU, the slower every individual task takes to process. This has a domino effect
 * in that it means more work gets backlogged. As the result you’ll end up with more and more threads
 * spawned making task processing even slower. This negative feedback loop is a hard problem to solve.
 *
 * So for almost all intents and purposes, Executors::newFixedThreadPool(int nThreads) should be your
 * goto choice for when you need a thread pool. For computationally intensive tasks it will probably give
 * you close to optimal throughput and for IO heavy tasks you won’t be that much worse than anything else.
 * At least until you profile that you got a problem with this kind of executor, you shouldn’t mindlessly
 * try to optimize it.
 */
public class App {

    public static void main(String[] args) throws Exception {

        Connection.getInstance().connect();
        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i =0 ; i < 200; i++){

            executorService.submit(()->{
                try {
                    Connection.getInstance().connect();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }


}
