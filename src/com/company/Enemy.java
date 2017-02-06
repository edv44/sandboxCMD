package com.company;

/**
 * Created by edv44 on 30.01.2017.
 */
public class Enemy {
    public String name;
    public int attack;
    public int defense;
    public int curHp;
    public int maxHp;
    public int exp;
    public int level;
    public int money;
    String[] names = {"Wolf", "Bear", "Boar"};

    public Enemy(int _heroLevel) {
        level = Helper.getRandom(_heroLevel, _heroLevel + 3);
        name = names[Helper.getRandom(1, names.length - 1)];
        attack = 16 * level;
        defense = 2 * level;
        maxHp = 44 * level;
        exp = 23 * level;
        curHp = maxHp;
        money = Math.round(attack + defense + maxHp) / 3;
    }
}