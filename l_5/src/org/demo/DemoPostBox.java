package org.demo;

public class DemoPostBox {
    public static void main(String[] args) {
        PostBox postBox = new PostBox();
        new Thread(new LetterProducer(postBox, generateLetters(10))).start();
        new Thread(new LetterConsumer(postBox)).start();
    }

    private static String [] generateLetters(int count){
        String [] letters = new String[count];
        for (int i = 0; i < count; i++) {
            letters[i] = "Letter " + (i +1);
        }
        return letters;
    }
 }
