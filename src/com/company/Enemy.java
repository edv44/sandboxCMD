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

    public Enemy(int _heroLocation) {
        name = "Zmey";
        attack = 16 * _heroLocation;
        defense = 2 * _heroLocation;
        maxHp = 44 * _heroLocation;
        curHp = maxHp;
        exp = 23 * _heroLocation;
    }
}