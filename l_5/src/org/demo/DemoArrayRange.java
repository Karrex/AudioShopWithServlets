package org.demo;

import java.util.Random;

public class DemoArrayRange {
    public static void main(String[] args) {
        DemoArrayRange demoArrayRange = new DemoArrayRange();
        int[] array = demoArrayRange.generateArray(100, false);
        demoArrayRange.executeTasks(array, 2);
    }

    private int[] generateArray(final int count, boolean fillRandomly) {
        Random random = new Random();
        int[] array = new int[count];
        for (int i = 0; i < count; i++) {
            if (fillRandomly) {
                array[i] = random.nextInt(count);
            } else {
                array[i] = i;
            }
        }
        return array;
    }

    private void executeTasks(int[] array, int tasks) {
        for (int i = 0; i < tasks; i++) {
            int rangeLength = array.length / tasks;
            Thread thread = new Thread(new ArrayRangeTask(i * rangeLength, (i + 1) * rangeLength, array), "ArrayRange - " + (i + 1));
            thread.start();
        }
    }
}
