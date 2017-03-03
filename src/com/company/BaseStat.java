package com.company;

import java.util.ArrayList;

enum StatType {
    STRENGTH, AGILITY, VITALITY, INTELLECT, ATTACK, DEFENSE, HP
}

abstract class BaseStat {
    int baseValue;
    int modValue;

    ArrayList<EquipableItem> adjust = new ArrayList<>(); //keep item link that gives this stat

    BaseStat(int value) {
        baseValue = value;
    }

    void addMod(EquipableItem item) {
        adjust.add(item);
    }

    void removeMod(EquipableItem item) {
        adjust.remove(item);
    }
}

class Stat extends BaseStat {
    private StatType type;

    Stat(StatType type, int value) {
        super(value);
        this.type = type;
    }

    void calc() {
        modValue = baseValue;
//        for (EquipableItem item : adjust) {
//        for (EquipableItem item : Hero.getInstance().) {
//            modValue += item.itemStats.get(this.type);
//        }
    }
}