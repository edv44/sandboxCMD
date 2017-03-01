package com.company;

import java.util.HashMap;
import java.util.Map;

abstract class HeroClass {
    String name;
    int hpMax;
    int attack;
    int defense;
    Map<StatType, Integer> stats = new HashMap();
}

abstract class ClassWarrior extends HeroClass {

    ClassWarrior() {
        name = "Warrior";
        hpMax = 100;
        attack = 700; //7
        defense = 7;
//        itemStats.put(StatType.HP, 100);
//        itemStats.put(StatType.ATTACK, 700);
//        itemStats.put(StatType.DEFENSE, 7);
        stats.put(StatType.STRENGTH, 6);
        stats.put(StatType.AGILITY, 4);
        stats.put(StatType.VITALITY, 7);
        stats.put(StatType.INTELLECT, 3);
    }
}

abstract class ClassRogue extends HeroClass {

    ClassRogue() {
        name = "Rogue";
        hpMax = 80;
        attack = 10;
        defense = 6;
//        itemStats.put(StatType.ATTACK, 10);
//        itemStats.put(StatType.DEFENSE, 6);
//        itemStats.put(StatType.HP, 80);
        stats.put(StatType.STRENGTH, 5);
        stats.put(StatType.AGILITY, 6);
        stats.put(StatType.VITALITY, 5);
        stats.put(StatType.INTELLECT, 4);
    }
}