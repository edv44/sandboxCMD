package com.company;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by edv44 on 30.01.2017.
 */
public class Helper {
    private static Random r = new Random();
    public static Scanner in = new Scanner(System.in);

    public static String read() {
        return (in.next());
    }

    public static void threadSleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static int getRandom(int min, int max) {
        return r.nextInt((max - min) + 1) + min;
    }

    public static boolean hit(Hero hero, Enemy enemy) {
        int heroDamage = hero.characterAttack - enemy.defense;

        enemy.curHp -= heroDamage;
        if (enemy.curHp <= 0) {
            enemy.curHp = 0;
            hero.monsterCounter++;
            hero.characterCurExp += enemy.exp; //lvl up not released
            hero.inventory.add(generateItem());
            System.out.printf("\n%s[%s/%s] hits %s[%s/%s] by %s damage.\n\n", hero.characterName, hero.characterCurHp, hero.characterHp, enemy.name, enemy.curHp, enemy.maxHp, heroDamage); //hit info
            System.out.printf("Winner: %s.\nEarned: %s exp, %s.\n", hero.characterName, enemy.exp, hero.inventory.get(hero.inventory.size() - 1).name); //win info
            return true;
        } else {
            System.out.printf("\n%s[%s/%s] hits %s[%s/%s] by %s damage.", hero.characterName, hero.characterCurHp, hero.characterHp, enemy.name, enemy.curHp, enemy.maxHp, heroDamage); //hit info
            return false;
        }
    }

    public static boolean hit(Enemy enemy, Hero hero) {
        int damage = enemy.attack - hero.characterDefense;

        hero.characterCurHp -= damage;
        if (hero.characterCurHp <= 0) {
            hero.characterCurHp = 0;
            System.out.printf("\n%s[%s/%s] hits %s[%s/%s] by %s damage.\n", enemy.name, enemy.curHp, enemy.maxHp, hero.characterName, hero.characterCurHp, hero.characterHp, damage); //hit info
            System.out.printf("Winner: %s.\n", enemy.name); //win info
            return true;
        } else {
            System.out.printf("\n%s[%s/%s] hits %s[%s/%s] by %s damage.\n", enemy.name, enemy.curHp, enemy.maxHp, hero.characterName, hero.characterCurHp, hero.characterHp, damage); //hit info
            return false;
        }
    }

    public static Item generateItem() {
        return new Item();
    }

    public static void clearScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public static void randomTest() { //just for test rnd(min, max)
        for (int i = 0; i < 100; i++) {
            System.out.println(getRandom(10, 95));
        }
    }
}
