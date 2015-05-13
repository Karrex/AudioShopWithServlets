package org.demo;

import java.util.logging.Logger;

public class IndexedStringList {
    private static final Logger l = Logger.getLogger(IndexedStringList.class.getSimpleName());

    private String[] data;
    private int currentIndex = 0;

    public IndexedStringList() {
        data = new String[5];
        l.info("created list by default for 5 elements only");
    }

    public IndexedStringList(int capacity) {
        this.data = new String[capacity];
        l.info("created list for " + capacity + " elements");
    }

    public void add(String item) {
        if (size() == data.length) {
            extendArray();
        }
        l.info("added item " + item + " at position " + currentIndex);
        data[currentIndex] = item;
        currentIndex++;
        l.info("list contains " + currentIndex + " from " + data.length + " all ");
    }

    public String get(int index) {
        if (index < 0 && index > currentIndex) {
            throw new RuntimeException("Wrong index");
        }
        l.info("get item from position " + index);
        return data[index];
    }

    public void remove(int index) {
        if (index < 0 && index > currentIndex) {
            throw new RuntimeException("Wrong index");
        }
        l.info("remove item from position " + index);
        for (int i = index; i < currentIndex; i++) {
            data[index] = data[index + 1];
        }
        currentIndex--;
        l.info("list contains " + currentIndex + " from " + data.length + " all ");
    }

    public void insert(int index, String item) {
        if (index < 0 && index > currentIndex) {
            throw new RuntimeException("Wrong index");
        }
        if (index == currentIndex) {
            add(item);
        } else {
            l.info("insert item to position " + index);
            if (size() + 1 == data.length) {
                extendArray();
            }
            System.arraycopy(data, index, data, index + 1,
                    currentIndex - index);
            data[index] = item;
            currentIndex++;

            l.info("list contains " + currentIndex + " from " + data.length + " all ");
        }
    }

    public int size() {
        return currentIndex;
    }

    private void extendArray() {
        String[] newArray = new String[data.length * 2];
        l.info("copy old data to new array " + newArray.length);
        System.arraycopy(data, 0, newArray, 0, data.length);
        data = newArray;
    }
}
