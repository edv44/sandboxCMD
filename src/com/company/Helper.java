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

    public static void clearScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
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
        int heroDamage = _hero.attack - _enemy.defense;

        _enemy.curHp -= heroDamage;
        if (_enemy.curHp <= 0) {
            _enemy.curHp = 0;
            _hero.monsterCounter++;
            drop(_hero, _enemy);
            System.out.printf("\n%s[%s/%s] hits %s[%s/%s] by %s damage.\n\n", _hero.name, _hero.hpCur, _hero.hpMax, _enemy.name, _enemy.curHp, _enemy.maxHp, heroDamage); //hit info
            System.out.printf("Winner: %s.\nEarned: %s exp, %s.\n", _hero.name, _enemy.exp, _hero.inventory.get(_hero.inventory.size() - 1).name); //win info
            lvlUp(_hero, _enemy);
            return true;
        } else {
            System.out.printf("\n%s[%s/%s] hits %s[%s/%s] by %s damage.", _hero.name, _hero.hpCur, _hero.hpMax, _enemy.name, _enemy.curHp, _enemy.maxHp, heroDamage); //hit info
            return false;
        }
    }

    public static boolean hit(Enemy _enemy, Hero _hero) {
        int damage = _enemy.attack - _hero.defense;

        _hero.hpCur -= damage;
        if (_hero.hpCur <= 0) {
            _hero.hpCur = 0;
            System.out.printf("\n%s[%s/%s] hits %s[%s/%s] by %s damage.\n", _enemy.name, _enemy.curHp, _enemy.maxHp, _hero.name, _hero.hpCur, _hero.hpMax, damage); //hit info
            System.out.printf("Winner: %s.\n", _enemy.name); //win info
            return true;
        } else {
            System.out.printf("\n%s[%s/%s] hits %s[%s/%s] by %s damage.\n", _enemy.name, _enemy.curHp, _enemy.maxHp, _hero.name, _hero.hpCur, _hero.hpMax, damage); //hit info
            return false;
        }
    }

    public static void drop(Hero _hero, Enemy _enemy) {
        _hero.money += _enemy.money;
        _hero.inventory.add(generateItem(_enemy.level, new Item(getItemQuality(), getItemType())));
    }

    public static void lvlUp(Hero _hero, Enemy _enemy) {
        _hero.expCur += _enemy.exp;
        if (_hero.expCur >= _hero.expMax) {
            _hero.level++;
            _hero.expCur -= _hero.expMax;
            _hero.expMax += 100 + _hero.expMax * 0.15;
            _hero.statPoints += 5;
            _hero.skillPoints += 1;
            System.out.printf("\nCongratulations! %s reached level %s.\nNow you have free %s skill and %s stat points.\n", _hero.name, _hero.level, _hero.skillPoints, _hero.statPoints);
        }
    }

    public static itemQuality getItemQuality() {
        itemQuality q;
        int x = getRandom(1, 100);
        if (x <= 50) q = itemQuality.NORMAL;
        else if (x > 50 && x <= 80) q = itemQuality.MAGIC;
        else if (x > 80 && x <= 95) q = itemQuality.RARE;
        else q = itemQuality.EPIC;
        return q;
    }

    public static itemType getItemType() {
        itemType t;
        int y = getRandom(1, 100);
        if (y <= 50) t = itemType.WEAPON;
        else t = itemType.ARMOR;
        return t;
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
                _item.name = _item.names[r.nextInt(_item.names.length)] + " [" + Helper.getRandom(0, 9999) + "] [" + _item.quality + "]";
                _item.addAttack = _enemyLvl * _item.amplifier;
                break;
            case ARMOR:
                _item.name = "Chest [" + Helper.getRandom(0, 9999) + "] [" + _item.quality + "]";
                _item.addHp = 25 * _enemyLvl * _item.amplifier;
                _item.addDefense = _enemyLvl * _item.amplifier;
                break;
        }
        _item.price = 50 * _item.amplifier * 5;
        return _item;
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