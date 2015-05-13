package org.demo;

import java.util.logging.Logger;

public class TwoLinkedStringList {
    private static final Logger l = Logger.getLogger(OneLinkedStringList.class.getSimpleName());

    private TwoListStringNode head;
    private TwoListStringNode tail;
    private int size = 0;

    public TwoLinkedStringList() {
        this.head = new TwoListStringNode(null);
        this.tail = new TwoListStringNode(null);
        this.head.setNext(tail);
        this.tail.setPrev(head);

        debugList();
    }

    private void debugList() {
        l.info("list with head " + head);
        l.info("list with head " + head.toStringPrevious());

        l.info("list with tail " + tail);
        l.info("list with head " + tail.toStringPrevious());
    }

    public void add(String item) {
        TwoListStringNode last = tail.getPrev();

        TwoListStringNode node = new TwoListStringNode(item);
        l.info("created new node " + node);
        node.setPrev(last);
        node.setNext(tail);

        l.info("added new node " + node);

        tail.setPrev(node);
        last.setNext(node);
        debugList();
        size++;
    }

    public String get(int index) {
        if (index > size()) {
            throw new RuntimeException("Wrong index");
        }
        TwoListStringNode currentNode = head;
        l.info("list take head " + head);
        for (int i = 1; i < index; i++) {
            if (currentNode.getNext() == null)
                throw new RuntimeException("");
            l.info("take current node at index " + index+ " -- " + currentNode);
            currentNode = currentNode.getNext();
        }
        l.info("take node at index " + index+ " -- " + currentNode);
        return currentNode.getValue();
    }

    public void remove(int index) {
        if (index < 0 && index > size()) {
            throw new RuntimeException("Wrong index");
        }
        TwoListStringNode currentNode = head;
        for (int i = 1; i < index; i++) {
            if (currentNode.getNext() == null)
                throw new RuntimeException("");
            l.info("take current node at index " + index+ " -- " + currentNode);
            currentNode = currentNode.getNext();
        }
        currentNode.getPrev().setNext(currentNode.getNext());
        currentNode.setNext(currentNode.getNext().getNext());
        debugList();
        size--;
    }

    public void insert(int index, String item) {
        if (index < 0 && index > size()) {
            throw new RuntimeException("Wrong index");
        }
        TwoListStringNode node = new TwoListStringNode(item);
        l.info("created new node " + node);
        TwoListStringNode currentNode = head;
        for (int i = 1; i < index; i++) {
            if (currentNode.getNext() == null)
                throw new RuntimeException("");
            l.info("take current node at index " + index+ " -- " + currentNode);
            currentNode = currentNode.getNext();
        }

        node.setPrev(currentNode);
        node.setNext(currentNode.getNext());
        currentNode.setNext(node);
        debugList();
        size++;
    }

    public int size() {
        return size;
    }
}
