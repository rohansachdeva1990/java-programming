package com.rohan.java.concurrency.concepts.multiplelocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by rohan on 16/03/2017.
 */

/**
 *
 * Due to synchronized method we are now controlling mutual access to
 * the List1 and List2. So, thats why synchronized method are not preferred
 *
 * As in our scenario Stage one is working on List1 and Stage 2 is working on List2.
 *
 * They can still work independently without any problems
 *
 * Starting...
 Time taken: 5397
 List 1 size: 2000
 List 2 size: 2000

 * It is taking long time
 *
 * synchronized block acquire intrinsic lock of this thread
 *
 *  * So if T1, is holding the intrinsic lock in stageOne, Any other thread cannot even
 * process stageTwo because the other threads have to wait for the only intrinsic lock
 * available to be free.
 *
 *
 */
public class SyncWorker {

    private Random random = new Random();
    private List<Integer> integerList1 = new ArrayList<>();
    private List<Integer> integerList2 = new ArrayList<>();

    /**
     * Get some information from somewhre
     */
    public synchronized void stageOne() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        integerList1.add(random.nextInt(100));
    }

    public synchronized void stageTwo() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        integerList2.add(random.nextInt(100));
    }


    public void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }


    /**
     * This may result in interleaving of threads.
     *
     * Exception in thread "Thread-0" java.lang.ArrayIndexOutOfBoundsException: 33
     at java.util.ArrayList.add(ArrayList.java:459)
     at com.rohan.multithreading.multiplelocks.Worker.stageTwo(Worker.java:36)
     at com.rohan.multithreading.multiplelocks.Worker.process(Worker.java:43)
     at com.rohan.multithreading.multiplelocks.Worker$1.run(Worker.java:54)
     at java.lang.Thread.run(Thread.java:745)
     *
     *
     * This occur because the List is being used from multiple threads and writing to a
     * list is not a single step operation. This makes all kind of crazy problems
     *
     */
    public void main() {
        System.out.println("Starting...");
        long startTimeInMs = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTimeInMs = System.currentTimeMillis();

        System.out.println("Time taken: " + (endTimeInMs - startTimeInMs));

        System.out.println("List 1 size: " + integerList1.size());
        System.out.println("List 2 size: " + integerList2.size());
    }

}
