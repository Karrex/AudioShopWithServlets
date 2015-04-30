package org.demo;

import java.util.Random;

public class LetterConsumer implements Runnable {
    private final PostBox postBox;

    public LetterConsumer(PostBox postBox) {
        this.postBox = postBox;
    }

    public void run() {
        Random random = new Random();
        for (String message = postBox.takeLetter(); !message.equals("FINISHED");
             message = postBox.takeLetter()) {
            System.out.format("MESSAGE RECEIVED: %s%n", message);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}