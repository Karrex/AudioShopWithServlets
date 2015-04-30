package org.demo;

public class ArrayRangeTask implements Runnable {
    private final int start;
    private final int end;
    private final int[] array;

    public ArrayRangeTask(int start, int end, int[] array) {
        this.start = start;
        this.end = end;
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            System.out.println(Thread.currentThread().getName() + "\t array[" + i + "] = " + array[i]);
        }
    }
}
