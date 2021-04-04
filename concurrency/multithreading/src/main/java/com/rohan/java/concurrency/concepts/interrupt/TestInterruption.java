package com.rohan.java.concurrency.concepts.interrupt;

/**
Some important links

https://www.yegor256.com/2015/10/20/interrupted-exception.html
https://www.ibm.com/developerworks/library/j-jtp05236/

*/
public class TestInterruption {

    static class Child implements Runnable {

        @Override
        public void run() {
            try {

                while (true) {
                    Thread.sleep(100);
                    System.err.println("In Child : " + Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                //System.err.println("Child: Interrupted : " + Thread.currentThread().isInterrupted());
                // Restore the interrupted status
                // not really needed here as we know the thread is exiting
                // but a good practice all the same. (So callers know
                // we've been interrupted.)

                Thread.currentThread().interrupt(); // Help propagating the exception to other threads

                System.err.println("Child: interrupted and exiting... is Interrupted : " + Thread.currentThread().isInterrupted());
            }

            try {
                Thread.sleep(5000);
                System.err.println("Child done sleeping");
            } catch (InterruptedException e) {
                System.err.println("Child interrupted sleeping again");
            }
        }
    }

    static class Friend implements Runnable {

        private Thread child;

        public Friend(Thread child) {
            this.child = child;
        }

        @Override
        public void run() {
            System.err.println("In Friend : " + Thread.currentThread().getName());

            while (!child.isInterrupted()) {
                try {
                    System.err.println("Friend name : " + Thread.currentThread().getName());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

         /*   try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            //System.out.println("Child interrupted : " +  child.isInterrupted());

            System.err.println("Friend: Found child interrupted and now exiting...");
        }
    }


    // Main Thread
    public static void main(String[] args) {

        Thread childThread = new Thread(new Child());
        childThread.setName("Child Thread");
        Thread friendThread = new Thread(new Friend(childThread));
        friendThread.setName("Friend Thread");

        System.out.println("Starting child and friend thread...");
        friendThread.start();
        childThread.start();

        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            System.err.println("In Main: Caught interrupted exception");
        }
        childThread.interrupt();

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.err.println("Main exiting");
    }
}
