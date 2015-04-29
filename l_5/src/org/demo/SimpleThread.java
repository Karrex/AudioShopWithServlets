package org.demo;

import java.util.logging.Logger;

public class SimpleThread extends Thread {
    private static final Logger logger = Logger.getLogger(SimpleThread.class.getName());
    @Override
    public void run() {
        logger.info("Hello from thread!" + this.getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
