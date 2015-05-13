package org.demo;

public class OneListStringNode {

    private String value;

    private OneListStringNode next;

    public OneListStringNode(String value, OneListStringNode next) {
        this.value = value;
        this.next = next;
    }

    public OneListStringNode(String value) {
        this(value, null);
    }

    public OneListStringNode getNext() {
        return next;
    }

    public void setNext(OneListStringNode node) {
        next = node;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{value = " + value + ", next => " + next + "}";
    }
}
