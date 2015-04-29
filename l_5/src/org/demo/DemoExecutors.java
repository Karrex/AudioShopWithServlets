package org.demo;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class DemoExecutors {

    private static final Logger logger = Logger.getLogger(DemoExecutors.class.getName());

    public static void main(String[] args) {
        DemoExecutors executors = new DemoExecutors(2);
        Runnable [] runnables = new Runnable[10];
        for(int i = 0; i < runnables.length; i++){
            runnables[i] = new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        logger.info(j + " - Hello from thread!" + Thread.currentThread().getName());
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            };
        }
        executors.executeTasks(runnables);
    }

    private final Executor executor;

    public DemoExecutors(int threads) {
        executor = Executors.newFixedThreadPool(threads);
    }

    private void executeTasks(Runnable... tasks) {
        for(Runnable task : tasks){
            executor.execute(task);
        }
    }
}
