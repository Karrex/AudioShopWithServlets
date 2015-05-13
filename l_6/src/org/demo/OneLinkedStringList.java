package org.demo;

import java.util.logging.Logger;

public class OneLinkedStringList {
    private static final Logger l = Logger.getLogger(OneLinkedStringList.class.getSimpleName());

    private OneListStringNode head;
    private int size = 0;

    public OneLinkedStringList() {
        this.head = new OneListStringNode(null);
        l.info("list with head " + head);
    }

    public void add(String item) {
        OneListStringNode node = new OneListStringNode(item);
        l.info("created new node " + node);

        OneListStringNode currentNode = head;
        l.info("list take head " + head);
        while (currentNode.getNext() != null){
            l.info("take current node " + currentNode);
            currentNode = currentNode.getNext();
        }
        l.info("take least node " + currentNode);
        currentNode.setNext(node);
        l.info("added new node " + currentNode);
        size++;
    }

    public String get(int index) {
        if (index > size()) {
            throw new RuntimeException("Wrong index");
        }
        OneListStringNode currentNode = head;
        l.info("list take head " + head);
        for (int i = 1; i < index; i++) {
            if (currentNode.getNext() == null)
                throw new RuntimeException("Too long");
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
        OneListStringNode currentNode = head;
        l.info("list take head " + head);
        for (int i = 1; i < index; i++) {
            if (currentNode.getNext() == null)
                throw new RuntimeException("Too long");
            l.info("take current node at index " + index+ " -- " + currentNode);
            currentNode = currentNode.getNext();
        }
        l.info("take node at index " + index+ " -- " + currentNode);
        currentNode.setNext(currentNode.getNext().getNext());
        l.info("take node at index " + index + " -- " + currentNode);
        size--;
    }

    public void insert(int index, String item) {
        if (index < 0 && index > size()) {
            throw new RuntimeException("Wrong index");
        }
        OneListStringNode node = new OneListStringNode(item);
        l.info("created new node " + node);

        OneListStringNode currentNode = head;
        l.info("list take head " + head);
        for (int i = 1; i < index; i++) {
            if (currentNode.getNext() == null)
                throw new RuntimeException("Too long");
            l.info("take current node at index " + index+ " -- " + currentNode);
            currentNode = currentNode.getNext();
        }

        l.info("take node at index " + index+ " -- " + currentNode);
        node.setNext(currentNode.getNext());
        l.info("add node " + index+ " -- " + node);
        currentNode.setNext(node);
        l.info("take node at index " + index+ " -- " + currentNode);
        size++;
    }

    public int size() {
        return size;
    }
}
