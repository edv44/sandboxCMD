package com.company;

import java.util.ArrayList;

enum StatType {
    STRENGTH, AGILITY, VITALITY, INTELLECT, ATTACK, DEFENSE, HP
}

abstract class BaseStat {
    int baseValue;
    int modValue;

    ArrayList<EquipableItem> adjust;

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
    StatType type;

    Stat(StatType type, int value) {
        super(value);
        this.type = type;
    }

    void calc() {
//        modValue = baseValue;
//        for(EquipableItem adj : adjust) modValue+=adj.setStats.put(type, baseValue);   //.setStats[type];
//        this.setStats.put(StatType.STRENGTH, 5);//test
    }
}

//    ArrayList<EquipableItem> adjust{
//        1 Quilled Armor; Map<StatType, Integer>
//        setStats {
//        DEFENSE, 5
//        HP, 15
// }
//        2 Quilled Armor;
//        3 Quilled Armor;
//        4 Quilled Armor;
//        5 Quilled Armor;
//        }