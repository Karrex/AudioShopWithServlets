package org.demo;

import java.util.Random;

public class LetterProducer implements Runnable {
    private final PostBox postBox;
    private final String[] letters;

    public LetterProducer(PostBox postBox, String ... letters) {
        this.postBox = postBox;
        this.letters = letters;
    }

    public void run() {
        Random random = new Random();

        for (String message : letters) {
            postBox.putLetter(message);
            System.out.format("MESSAGE SENT: %s%n", message);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        postBox.putLetter("FINISHED");
    }
}