package com.company;

abstract class HeroClass {
    String name;
    int hpMax;
    int attack;
    int defense;
    int strength;
    int agility;
    int vitality;
    int intellect;
}

abstract class ClassWarrior extends HeroClass {

    ClassWarrior() {
        name = "Warrior";
        hpMax = 100;
        attack = 700; //7
        defense = 7;
        strength = 6;
        agility = 4;
        vitality = 7;
        intellect = 3;
    }
}

abstract class ClassRogue extends HeroClass {

    ClassRogue() {
        name = "Rogue";
        hpMax = 80;
        attack = 10;
        defense = 6;
        strength = 5;
        agility = 6;
        vitality = 5;
        intellect = 4;
    }
}