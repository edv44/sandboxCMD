package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract class HeroClass {
    String name;
    int hpMax;
    int attack;
    int defense;
//    final Map<StatType, Integer> stats = new HashMap(); //logic v2
    ArrayList<Stat> stats = new ArrayList<>();
}

abstract class ClassWarrior extends HeroClass {

    ClassWarrior() {
        name = "Warrior";
        hpMax = 100;
        attack = 9000; //7
        defense = 7;
//        itemStats.put(StatType.HP, 100);
//        itemStats.put(StatType.ATTACK, 700);
//        itemStats.put(StatType.DEFENSE, 7);
//        stats.put(StatType.STRENGTH, 10); //6 //logic v2
//        stats.put(StatType.AGILITY, 10); //4 //logic v2
//        stats.put(StatType.VITALITY, 10); //7 //logic v2
//        stats.put(StatType.INTELLECT, 10); //3 //logic v2
        stats.add(new Stat(StatType.STRENGTH, 10)); //6
        stats.add(new Stat(StatType.AGILITY, 10)); //4
        stats.add(new Stat(StatType.VITALITY, 10)); //7
        stats.add(new Stat(StatType.INTELLECT, 10)); //3

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
//        stats.put(StatType.STRENGTH, 5); //logic v2
//        stats.put(StatType.AGILITY, 6); //logic v2
//        stats.put(StatType.VITALITY, 5); //logic v2
//        stats.put(StatType.INTELLECT, 4); //logic v2
        stats.add(new Stat(StatType.STRENGTH, 5));
        stats.add(new Stat(StatType.AGILITY, 6));
        stats.add(new Stat(StatType.VITALITY, 5));
        stats.add(new Stat(StatType.INTELLECT, 4));
    }
}