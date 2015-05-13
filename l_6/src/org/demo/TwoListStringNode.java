package org.demo;

public class TwoListStringNode {

    private String value;

    private TwoListStringNode next;

    private TwoListStringNode prev;

    public TwoListStringNode(String value, TwoListStringNode next) {
        this.value = value;
        this.next = next;
    }

    public TwoListStringNode(String value) {
        this(value, null);
    }

    public TwoListStringNode getNext() {
        return next;
    }

    public void setNext(TwoListStringNode node) {
        next = node;
    }


    public TwoListStringNode getPrev() {
        return prev;
    }

    public void setPrev(TwoListStringNode prev) {
        this.prev = prev;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{value = " + value + ", next => " + next  + "}";
    }

    public String toStringPrevious() {
        return "{value = " + value + ", previous => " + prev  + "}";
    }
}
