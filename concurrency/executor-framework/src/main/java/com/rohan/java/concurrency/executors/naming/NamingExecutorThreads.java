package com.rohan.java.concurrency.executors.naming;

import com.rohan.concurrency.common.tasks.LoopTaskC;
import com.rohan.concurrency.common.factory.NamedThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Two ways:-
 *
 * 1. From within the task -at 'thread-running' time. Similar to the technique that was used for renaming normal
 * threads.
 *
 * 2. Specify the thread-naming strategy at 'executor-creation' time.
 *
 * Pool sequence number and thread sequence number always start from 1 by default.
 *
 * but, with normal way, thread - 0 is used by the jvm
 *
 * Before:-
 *
 * ###### [pool-1-thread-1] <TASK-loopTaskC-1> STARTING ######
 * ###### [pool-1-thread-1] <TASK-loopTaskC-1> TICK TICK 1
 * ###### [pool-1-thread-2] <TASK-loopTaskC-2> STARTING ######
 * ###### [pool-1-thread-2] <TASK-loopTaskC-2> TICK TICK 0
 * ###### [pool-1-thread-3] <TASK-loopTaskC-3> TICK TICK 0
 *
 * After:
 *
 * ###### [MyThread-1] <TASK-loopTaskC-1> TICK TICK 7
 * ###### [MyThread-2] <TASK-loopTaskC-2> TICK TICK 7
 * ###### [MyThread-1] <TASK-loopTaskC-1> TICK TICK 8
 * ###### [MyThread-2] <TASK-loopTaskC-2> TICK TICK 8
 *
 */
public class NamingExecutorThreads {

    public static void main(String[] args) {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        //ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService = Executors.newCachedThreadPool(new NamedThreadFactory());

        executorService.execute(new LoopTaskC());
        executorService.execute(new LoopTaskC());
        executorService.execute(new LoopTaskC());

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}
