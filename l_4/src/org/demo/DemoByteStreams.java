package org.demo;

import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DemoByteStreams {

    private static final Logger logger = Logger.getLogger(DemoByteStreams.class.getName());

    public static void main(String[] args) {
        File file = new File("demo.txt");

        DemoByteStreams demoByteStreams = new DemoByteStreams();

        demoByteStreams.writeFile(file);

        demoByteStreams.readFile(file);

        demoByteStreams.writeFileJava7(file);

        demoByteStreams.readFileJava7(file);
    }


    private void writeFile(File file) {
        BufferedOutputStream out = null;
        try {
            logger.log(Level.INFO, "try write to file - " + file.getAbsolutePath());
            out = new BufferedOutputStream(new FileOutputStream(file));
            byte[] data = "Hello World!".getBytes();
            logger.log(Level.INFO, "write - " + Arrays.toString(data));
            out.write(data);
            out.flush();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "error while writing to file " + file, e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "error while closing file " + file, e);
                }
            }
        }
    }

    private void readFile(File file) {
        BufferedInputStream in = null;
        try {
            logger.log(Level.INFO, "try read file - " + file.getAbsolutePath());
            in = new BufferedInputStream(new FileInputStream(file));
            byte[] data = new byte[1024];
            int readLength = -1;
            while ((readLength = in.read(data)) != -1) {
                String str = new String(data, 0, readLength);
                logger.log(Level.INFO, "read - " + str);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "error while reading file " + file, e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "error while closing file " + file, e);
                }
            }
        }
    }

    private void readFileJava7(File file){
        logger.log(Level.INFO, "try write to file - " + file.getAbsolutePath());
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            byte[] data = new byte[1024];
            int readLength = -1;
            while ((readLength = in.read(data)) != -1) {
                String str = new String(data, 0, readLength);
                logger.log(Level.INFO, "read - " + str);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "error while reading file " + file, e);
        }
    }

    private void writeFileJava7(File file) {
        logger.log(Level.INFO, "try write to file - " + file.getAbsolutePath());
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            byte[] data = "Hello World!".getBytes();
            logger.log(Level.INFO, "write - " + Arrays.toString(data));
            out.write(data);
            out.flush();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "error while writing to file " + file, e);
        }
    }
}
