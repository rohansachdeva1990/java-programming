package com.rohan.java.concurrency.naming.executors;

import com.rohan.java.concurrency.common.factory.NamedThreadFactory;
import com.rohan.java.concurrency.common.tasks.LoopTaskC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Two ways:-
 * <p>
 * 1. From within the task -at 'thread-running' time. Similar to the technique that was used for renaming normal
 * threads.
 * <p>
 * 2. Specify the thread-naming strategy at 'executor-creation' time.
 * <p>
 * Pool sequence number and thread sequence number always start from 1 by default.
 * <p>
 * but, with normal way, thread - 0 is used by the jvm
 * <p>
 * Before:-
 * <p>
 * ###### [pool-1-thread-1] <TASK-loopTaskC-1> STARTING ######
 * ###### [pool-1-thread-1] <TASK-loopTaskC-1> TICK TICK 1
 * ###### [pool-1-thread-2] <TASK-loopTaskC-2> STARTING ######
 * ###### [pool-1-thread-2] <TASK-loopTaskC-2> TICK TICK 0
 * ###### [pool-1-thread-3] <TASK-loopTaskC-3> TICK TICK 0
 * <p>
 * After:
 * <p>
 * ###### [MyThread-1] <TASK-loopTaskC-1> TICK TICK 7
 * ###### [MyThread-2] <TASK-loopTaskC-2> TICK TICK 7
 * ###### [MyThread-1] <TASK-loopTaskC-1> TICK TICK 8
 * ###### [MyThread-2] <TASK-loopTaskC-2> TICK TICK 8
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
