package org.demo;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DemoBridgeStreams {

    private static final Logger logger = Logger.getLogger(DemoBridgeStreams.class.getName());

    public static void main(String[] args) {
        File file = new File("demo.txt");

        DemoBridgeStreams demoByteStreams = new DemoBridgeStreams();

        demoByteStreams.writeFile(file, "Hello, World!", "Welcome!");

        demoByteStreams.readFile(file);

        demoByteStreams.writeFileJava7(file, "Hello, World!", "Welcome!");

        demoByteStreams.readFileJava7(file);
    }


    private void writeFile(File file, String... data) {
        BufferedWriter out = null;
        try {
            logger.log(Level.INFO, "try write to file - " + file.getAbsolutePath());
            if(data == null || data.length ==0 ){
                logger.log(Level.WARNING, "nothing to write");
                return;
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            for(String line : data) {
                logger.log(Level.INFO, "write - " + line);
                out.write(line);
                out.newLine();
                out.flush();
            }
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
        BufferedReader in = null;
        try {
            logger.log(Level.INFO, "try read file - " + file.getAbsolutePath());
            in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = null;
            while ((line = in.readLine()) != null) {
                logger.log(Level.INFO, "read - " + line);
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
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line = null;
            while ((line = in.readLine()) != null) {
                logger.log(Level.INFO, "read - " + line);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "error while reading file " + file, e);
        }
    }

    private void writeFileJava7(File file, String... data) {
        logger.log(Level.INFO, "try write to file - " + file.getAbsolutePath());
        if(data == null || data.length ==0 ){
            logger.log(Level.WARNING, "nothing to write");
            return;
        }
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
            for(String line : data) {
                logger.log(Level.INFO, "write - " + line);
                out.write(line);
                out.newLine();
                out.flush();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "error while writing to file " + file, e);
        }
    }
}
