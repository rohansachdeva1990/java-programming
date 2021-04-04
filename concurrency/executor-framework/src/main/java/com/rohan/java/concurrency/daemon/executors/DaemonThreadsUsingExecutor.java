package com.rohan.java.concurrency.daemon.executors;



import com.rohan.java.concurrency.common.factory.DaemonThreadFactory;
import com.rohan.java.concurrency.common.tasks.LoopTaskD;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DaemonThreadsUsingExecutor {

    public static void main(String[] args) {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        ExecutorService executorService = Executors.newCachedThreadPool(new DaemonThreadFactory());


        /*
        executorService.execute(new LoopTaskD(100));
        executorService.execute(new LoopTaskD(200));
        executorService.execute(new LoopTaskD(100));
        executorService.execute(new LoopTaskD(200));
        */

        executorService.execute(new LoopTaskD(100));
        executorService.execute(new LoopTaskD(200));
        executorService.execute(new LoopTaskD(300));
        executorService.execute(new LoopTaskD(400));

        executorService.shutdown();

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}

