package org.demo;

public class DemoThreads {
    public static void main(String[] args) {
        DemoThreads demoThreads = new DemoThreads();
        demoThreads.runRunnables(10);
        demoThreads.runThreads(10);
    }

    public void runThreads(int count){
        for (int i = 0; i < count; i++) {
            Thread thread = new SimpleThread();
            thread.start();
        }
    }

    public void runRunnables(int count){
        for (int i = 0; i < count; i++) {
            Thread thread = new Thread(new SimpleRunnable());
            thread.start();
        }
    }
}
