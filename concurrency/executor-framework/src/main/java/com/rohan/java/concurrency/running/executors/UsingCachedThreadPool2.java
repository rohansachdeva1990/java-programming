package com.rohan.java.concurrency.running.executors;

import com.rohan.java.concurrency.common.factory.NamedThreadFactory;
import com.rohan.java.concurrency.common.tasks.LoopTaskC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates use of threads to be re used in case of cachedThreadPool
 *
 * op:-
 *
 * Main thread starts here..
 ###### [MyThread-2] <TASK-loopTaskC-2> STARTING ######
 ###### [MyThread-2] <TASK-loopTaskC-2> TICK TICK 0
 ###### [MyThread-1] <TASK-loopTaskC-1> STARTING ######
 ###### [MyThread-1] <TASK-loopTaskC-1> TICK TICK 0
 ###### [MyThread-3] <TASK-loopTaskC-3> STARTING ######
 ###### [MyThread-3] <TASK-loopTaskC-3> TICK TICK 0
 ###### [MyThread-1] <TASK-loopTaskC-1> TICK TICK 1
 ###### [MyThread-2] <TASK-loopTaskC-2> TICK TICK 1
 ###### [MyThread-3] <TASK-loopTaskC-3> TICK TICK 1
 ###### [MyThread-3] <TASK-loopTaskC-3> TICK TICK 2
 ###### [MyThread-3] <TASK-loopTaskC-3> TICK TICK 3
 ###### [MyThread-2] <TASK-loopTaskC-2> TICK TICK 2
 ###### [MyThread-3] <TASK-loopTaskC-3> TICK TICK 4
 ###### [MyThread-1] <TASK-loopTaskC-1> TICK TICK 2
 ###### [MyThread-2] <TASK-loopTaskC-2> TICK TICK 3
 ###### [MyThread-3] <TASK-loopTaskC-3> TICK TICK 5
 ###### [MyThread-3] <TASK-loopTaskC-3> TICK TICK 6
 ###### [MyThread-1] <TASK-loopTaskC-1> TICK TICK 3
 ###### [MyThread-1] <TASK-loopTaskC-1> TICK TICK 4
 ###### [MyThread-2] <TASK-loopTaskC-2> TICK TICK 4
 ###### [MyThread-3] <TASK-loopTaskC-3> TICK TICK 7
 ###### [MyThread-1] <TASK-loopTaskC-1> TICK TICK 5
 ###### [MyThread-3] <TASK-loopTaskC-3> TICK TICK 8
 ###### [MyThread-2] <TASK-loopTaskC-2> TICK TICK 5
 ###### [MyThread-3] <TASK-loopTaskC-3> TICK TICK 9
 ###### [MyThread-1] <TASK-loopTaskC-1> TICK TICK 6
 ###### [MyThread-3] <TASK-loopTaskC-3> ENDING ######
 ###### [MyThread-2] <TASK-loopTaskC-2> TICK TICK 6
 ###### [MyThread-1] <TASK-loopTaskC-1> TICK TICK 7
 ###### [MyThread-1] <TASK-loopTaskC-1> TICK TICK 8
 ###### [MyThread-2] <TASK-loopTaskC-2> TICK TICK 7
 ###### [MyThread-1] <TASK-loopTaskC-1> TICK TICK 9
 ###### [MyThread-1] <TASK-loopTaskC-1> ENDING ######
 ###### [MyThread-2] <TASK-loopTaskC-2> TICK TICK 8
 ###### [MyThread-2] <TASK-loopTaskC-2> TICK TICK 9
 ###### [MyThread-2] <TASK-loopTaskC-2> ENDING ######
 ###### [MyThread-1] <TASK-loopTaskC-5> STARTING ######
 ###### [MyThread-3] <TASK-loopTaskC-6> STARTING ######
 ###### [MyThread-2] <TASK-loopTaskC-4> STARTING ######
 ###### [MyThread-2] <TASK-loopTaskC-4> TICK TICK 0
 ###### [MyThread-3] <TASK-loopTaskC-6> TICK TICK 0
 ###### [MyThread-1] <TASK-loopTaskC-5> TICK TICK 0
 ###### [MyThread-4] <TASK-loopTaskC-7> STARTING ######
 ###### [MyThread-4] <TASK-loopTaskC-7> TICK TICK 0
 ###### [MyThread-5] <TASK-loopTaskC-8> STARTING ######
 ###### [MyThread-5] <TASK-loopTaskC-8> TICK TICK 0
 Main thread ends here..
 ###### [MyThread-2] <TASK-loopTaskC-4> TICK TICK 1
 ###### [MyThread-2] <TASK-loopTaskC-4> TICK TICK 2
 ###### [MyThread-5] <TASK-loopTaskC-8> TICK TICK 1
 ###### [MyThread-4] <TASK-loopTaskC-7> TICK TICK 1
 ###### [MyThread-1] <TASK-loopTaskC-5> TICK TICK 1
 ###### [MyThread-3] <TASK-loopTaskC-6> TICK TICK 1
 ###### [MyThread-1] <TASK-loopTaskC-5> TICK TICK 2
 ###### [MyThread-4] <TASK-loopTaskC-7> TICK TICK 2
 ###### [MyThread-5] <TASK-loopTaskC-8> TICK TICK 2
 ###### [MyThread-2] <TASK-loopTaskC-4> TICK TICK 3
 ###### [MyThread-5] <TASK-loopTaskC-8> TICK TICK 3
 ###### [MyThread-5] <TASK-loopTaskC-8> TICK TICK 4
 ###### [MyThread-2] <TASK-loopTaskC-4> TICK TICK 4
 ###### [MyThread-3] <TASK-loopTaskC-6> TICK TICK 2
 ###### [MyThread-4] <TASK-loopTaskC-7> TICK TICK 3
 ###### [MyThread-1] <TASK-loopTaskC-5> TICK TICK 3
 ###### [MyThread-5] <TASK-loopTaskC-8> TICK TICK 5
 ###### [MyThread-1] <TASK-loopTaskC-5> TICK TICK 4
 ###### [MyThread-2] <TASK-loopTaskC-4> TICK TICK 5
 ###### [MyThread-4] <TASK-loopTaskC-7> TICK TICK 4
 ###### [MyThread-3] <TASK-loopTaskC-6> TICK TICK 3
 ###### [MyThread-2] <TASK-loopTaskC-4> TICK TICK 6
 ###### [MyThread-5] <TASK-loopTaskC-8> TICK TICK 6
 ###### [MyThread-1] <TASK-loopTaskC-5> TICK TICK 5
 ###### [MyThread-3] <TASK-loopTaskC-6> TICK TICK 4
 ###### [MyThread-4] <TASK-loopTaskC-7> TICK TICK 5
 ###### [MyThread-2] <TASK-loopTaskC-4> TICK TICK 7
 ###### [MyThread-3] <TASK-loopTaskC-6> TICK TICK 5
 ###### [MyThread-2] <TASK-loopTaskC-4> TICK TICK 8
 ###### [MyThread-5] <TASK-loopTaskC-8> TICK TICK 7
 ###### [MyThread-5] <TASK-loopTaskC-8> TICK TICK 8
 ###### [MyThread-2] <TASK-loopTaskC-4> TICK TICK 9
 ###### [MyThread-1] <TASK-loopTaskC-5> TICK TICK 6
 ###### [MyThread-4] <TASK-loopTaskC-7> TICK TICK 6
 ###### [MyThread-2] <TASK-loopTaskC-4> ENDING ######
 ###### [MyThread-1] <TASK-loopTaskC-5> TICK TICK 7
 ###### [MyThread-5] <TASK-loopTaskC-8> TICK TICK 9
 ###### [MyThread-3] <TASK-loopTaskC-6> TICK TICK 6
 ###### [MyThread-3] <TASK-loopTaskC-6> TICK TICK 7
 ###### [MyThread-4] <TASK-loopTaskC-7> TICK TICK 7
 ###### [MyThread-5] <TASK-loopTaskC-8> ENDING ######
 ###### [MyThread-1] <TASK-loopTaskC-5> TICK TICK 8
 ###### [MyThread-3] <TASK-loopTaskC-6> TICK TICK 8
 ###### [MyThread-4] <TASK-loopTaskC-7> TICK TICK 8
 ###### [MyThread-1] <TASK-loopTaskC-5> TICK TICK 9
 ###### [MyThread-1] <TASK-loopTaskC-5> ENDING ######
 ###### [MyThread-4] <TASK-loopTaskC-7> TICK TICK 9
 ###### [MyThread-3] <TASK-loopTaskC-6> TICK TICK 9
 ###### [MyThread-4] <TASK-loopTaskC-7> ENDING ######
 ###### [MyThread-3] <TASK-loopTaskC-6> ENDING ######
 */
public class UsingCachedThreadPool2 {

    public static void main(String[] args) {
        System.out.println("Main thread starts here..");

        // At all times total number of concurrent running task never gets exceeded.
        // Initializing phase
        ExecutorService executorService = Executors.newCachedThreadPool(new NamedThreadFactory());

        executorService.submit(new LoopTaskC());
        executorService.submit(new LoopTaskC());
        executorService.submit(new LoopTaskC());

        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.submit(new LoopTaskC());
        executorService.submit(new LoopTaskC());
        executorService.submit(new LoopTaskC());
        executorService.submit(new LoopTaskC());
        executorService.submit(new LoopTaskC());

        // The program wont stop until and unless we call shutdown method. Never forget ! Or there
        // will be memory leak
        executorService.shutdown();

        System.out.println("Main thread ends here..");
    }

}
