package com.company;

import java.util.ArrayList;

abstract class HeroClass {
    String name;
    int baseHP;
//    final Map<StatType, Integer> stats = new HashMap(); //logic v2
    ArrayList<Stat> stats = new ArrayList<>();
}

abstract class ClassWarrior extends HeroClass {

    ClassWarrior() {
        name = "Warrior";
        baseHP = 70;
//        stats.put(StatType.STRENGTH, 10); //6 //logic v2
//        stats.put(StatType.AGILITY, 10); //4 //logic v2
//        stats.put(StatType.VITALITY, 10); //7 //logic v2
//        stats.put(StatType.INTELLECT, 10); //3 //logic v2
        stats.add(new Stat(StatType.DAMAGE, 4));
        stats.add(new Stat(StatType.DEFENSE, 9));
        stats.add(new Stat(StatType.ACCURACY, 6));
        stats.add(new Stat(StatType.STRENGTH, 5));
        stats.add(new Stat(StatType.AGILITY, 4));
        stats.add(new Stat(StatType.VITALITY, 6));
        stats.add(new Stat(StatType.INTELLECT, 3));

    }
}

abstract class ClassRogue extends HeroClass {

    ClassRogue() {
        name = "Rogue";
        baseHP = 50;
//        stats.put(StatType.STRENGTH, 5); //logic v2
//        stats.put(StatType.AGILITY, 6); //logic v2
//        stats.put(StatType.VITALITY, 5); //logic v2
//        stats.put(StatType.INTELLECT, 4); //logic v2
        stats.add(new Stat(StatType.DAMAGE, 6));
        stats.add(new Stat(StatType.DEFENSE, 6));
        stats.add(new Stat(StatType.ACCURACY, 7));
        stats.add(new Stat(StatType.STRENGTH, 4));
        stats.add(new Stat(StatType.AGILITY, 5));
        stats.add(new Stat(StatType.VITALITY, 5));
        stats.add(new Stat(StatType.INTELLECT, 4));
    }
}