package org.demo;

public class PostBox {

    private String currentLetter;
    private boolean empty = true;

    public synchronized void putLetter(String letter){
        while (!empty){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        empty = false;
        currentLetter = letter;
        notify();
    }

    public synchronized String takeLetter(){
        while (empty){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        empty = true;
        notify();
        return currentLetter;
    }
}
