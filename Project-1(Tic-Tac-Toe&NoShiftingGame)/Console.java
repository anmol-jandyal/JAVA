package com.consoleFunctions;

import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

    final static String os = System.getProperty("os.name");
    // getProperty("os.name") returns the os name

    public static void clearScr() {

        if (os.contains("Windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
                System.out.println("handled");
            }
        } else {
            try {
                Runtime.getRuntime().exec(new String[] { " / bin / sh", "-c", "clear" });
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public static void pause() {
        System.out.print("\n\nenter to continue............");

        InputStreamReader reader = new InputStreamReader(System.in);

        int x = 0;

        try {
            x = reader.read(); // read the character and returns it ascii
        } catch (java.io.IOException e) {
            System.out.println("handled");

        }

    }
}

/*
 * ProcessBuilder is used to create the operating system processes
 * 
 */