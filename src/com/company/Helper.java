package com.company;

import java.util.Random;
import java.util.Scanner;

class Helper {
    private static Random r = new Random();
    private static Scanner in = new Scanner(System.in);

    static String read() {
        return (in.next());
    }

    static void clearScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    static void threadSleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static int getRandom(int min, int max) {
        return r.nextInt((max - min) + 1) + min;
    }
}