package com.rohan.java.concurrency.naming.threads;



import com.rohan.java.concurrency.common.tasks.LoopTaskC;

import java.util.concurrent.TimeUnit;

public class NamingThreadSecondWay {

    public static void main(String[] args) {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        new Thread(new LoopTaskC(), "MyThread - 1").start();

        Thread t2 = new Thread(new LoopTaskC());
        //t2.setName("MyThread - 2"); // From out side the class

        t2.start();


        try {
            TimeUnit.MILLISECONDS.sleep(900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * We can change the name after the thread is started. If we get the output directly from the thread name everytime.
         * We should avoid changing the name until and unless it is required.
         */
        t2.setName("MyThread - 2"); // From out side the class

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}
