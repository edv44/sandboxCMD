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

    public static void threadSleep(int _ms) {
        try {
            Thread.sleep(_ms);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static int getRandom(int _min, int _max) {
        return r.nextInt((_max - _min) + 1) + _min;
    }

    public static boolean hit(Hero _hero, Enemy _enemy) {
        int heroDamage = _hero.characterAttack - _enemy.defense;

        _enemy.curHp -= heroDamage;
        if (_enemy.curHp <= 0) {
            _enemy.curHp = 0;
            _hero.monsterCounter++;
            _hero.characterCurExp += _enemy.exp; //lvl up not released todo: lvlup
            _hero.money += _enemy.money;
            drop(_hero, _enemy);
            System.out.printf("\n%s[%s/%s] hits %s[%s/%s] by %s damage.\n\n", _hero.characterName, _hero.characterCurHp, _hero.characterHp, _enemy.name, _enemy.curHp, _enemy.maxHp, heroDamage); //hit info
            System.out.printf("Winner: %s.\nEarned: %s exp, %s.\n", _hero.characterName, _enemy.exp, _hero.inventory.get(_hero.inventory.size() - 1).name); //win info
            return true;
        } else {
            System.out.printf("\n%s[%s/%s] hits %s[%s/%s] by %s damage.", _hero.characterName, _hero.characterCurHp, _hero.characterHp, _enemy.name, _enemy.curHp, _enemy.maxHp, heroDamage); //hit info
            return false;
        }
    }

    public static boolean hit(Enemy _enemy, Hero _hero) {
        int damage = _enemy.attack - _hero.characterDefense;

        _hero.characterCurHp -= damage;
        if (_hero.characterCurHp <= 0) {
            _hero.characterCurHp = 0;
            System.out.printf("\n%s[%s/%s] hits %s[%s/%s] by %s damage.\n", _enemy.name, _enemy.curHp, _enemy.maxHp, _hero.characterName, _hero.characterCurHp, _hero.characterHp, damage); //hit info
            System.out.printf("Winner: %s.\n", _enemy.name); //win info
            return true;
        } else {
            System.out.printf("\n%s[%s/%s] hits %s[%s/%s] by %s damage.\n", _enemy.name, _enemy.curHp, _enemy.maxHp, _hero.characterName, _hero.characterCurHp, _hero.characterHp, damage); //hit info
            return false;
        }
    }

    public static void drop(Hero _hero, Enemy _enemy) {
        itemQuality quality;
        itemType type;
        int x = getRandom(1, 100);
        int y = getRandom(1, 100);
        if (x <= 50) quality = itemQuality.NORMAL;
        else if (x > 50 && x <= 80) quality = itemQuality.MAGIC;
        else if (x > 80 && x <= 95) quality = itemQuality.RARE;
        else quality = itemQuality.EPIC;
        if (y <= 50) type = itemType.WEAPON;
        else type = itemType.ARMOR;
        _hero.inventory.add(generateItem(_enemy.level, new Item(quality, type)));
    }

    public static Item generateItem(Integer _enemyLvl, Item _item) {
        switch (_item.quality) {
            case NORMAL:
                _item.amplifier = 1;
                break;
            case MAGIC:
                _item.amplifier = 1.2;
                break;
            case RARE:
                _item.amplifier = 1.5;
                break;
            case EPIC:
                _item.amplifier = 2;
                break;
        }

        switch (_item.type) {
            case WEAPON:
                //name = "Sword"; //r.next()
                //name = name[Helper.getRandom(0, name.length)];
                _item.name = "Axe [" + Helper.getRandom(0, 9999) + "] [" + _item.quality + "]";
                _item.addAttack = _enemyLvl * _item.amplifier;
                break;
            case ARMOR:
                _item.name = "Chest [" + Helper.getRandom(0, 9999) + "] [" + _item.quality + "]";
                _item.addHp = 25 * _enemyLvl * _item.amplifier;
                _item.addDefense = _enemyLvl * _item.amplifier;
                break;
        }
        return _item;
    }

    public static void clearScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    enum itemQuality {
        NORMAL, MAGIC, RARE, EPIC
    }

    enum itemType {
        ARMOR, WEAPON
    }

    public static void randomTest() { //just for test rnd(min, max)
        for (int i = 0; i < 100; i++) {
            System.out.println(getRandom(10, 95));
        }
    }
}
