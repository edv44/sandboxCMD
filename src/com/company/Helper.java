package com.company;

import java.util.Random;
import java.util.Scanner;

class Helper {
    private static Random r = new Random();
    static Scanner in = new Scanner(System.in);

    static String read() {
        return (in.next());
    }

    static void clearScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    static void threadSleep(int _ms) {
        try {
            Thread.sleep(_ms);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static int getRandom(int _min, int _max) {
        return r.nextInt((_max - _min) + 1) + _min;
    }
}