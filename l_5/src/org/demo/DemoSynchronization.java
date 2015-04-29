package org.demo;


import java.util.logging.Logger;

public class DemoSynchronization {

    private static final Logger logger = Logger.getLogger(DemoSynchronization.class.getName());

    private int counter = 100;


    public static void main(String[] args) {
        DemoSynchronization synchronization = new DemoSynchronization();
        synchronization.moveDown(5);
    }


    private void moveDown(int countThreads) {
        for (int i = 0; i < countThreads; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        decrement();
                    }
                }
            });
            thread.start();
        }
    }

    private /*synchronized*/ void decrement() {
        counter--;
        logger.info("counter = " + counter + " at thread " + Thread.currentThread().getName());
    }
}
