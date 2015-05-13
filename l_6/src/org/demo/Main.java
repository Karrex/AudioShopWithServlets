package org.demo;

public class Main {

    public static void main(String[] args) {
        demoIndexedList();
        demoOneLinkedList();
        demoTwoLinkedList();
    }

    private static void demoIndexedList() {
        IndexedStringList list = new IndexedStringList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        list.remove(3);
        list.remove(3);
        list.add("7");
        list.add("8");
        list.add("9");
    }

    private static void demoOneLinkedList() {
        OneLinkedStringList list = new OneLinkedStringList();
        list.add("1");
        list.add("2");
        list.add("3");

        list.remove(2);
        list.add("4");
    }

    private static void demoTwoLinkedList() {
        TwoLinkedStringList list = new TwoLinkedStringList();
        list.add("1");
        list.add("2");
        list.add("3");

        list.remove(2);
        list.add("4");
    }
}
