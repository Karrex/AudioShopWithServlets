package org.demo;


import java.util.logging.Logger;

public class SimpleRunnable implements Runnable {
    private static final Logger logger = Logger.getLogger(SimpleRunnable.class.getName());
    @Override
    public void run() {
        logger.info("Hello from thread!" + Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
