package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

enum ItemType {
    WEAPON, HELMET, CHEST, GLOVES, BELT, BOOTS
}

enum ItemQuality {
    NORMAL, MAGIC, RARE, EPIC
}

class Item {
    String name;
    int cost;

    Item() {
    }

    static ItemQuality getItemQuality() {
        ItemQuality q;
        int x = Helper.getRandom(1, 1000);
        if (x <= 500) q = ItemQuality.NORMAL;
        else if (x > 500 && x <= 800) q = ItemQuality.MAGIC;
        else if (x > 800 && x <= 950) q = ItemQuality.RARE;
        else q = ItemQuality.EPIC;
        return q;
    }

//    protected static ItemType getItemType() { //todo:add consumable?
//        ItemType t;
//        int y = Helper.getRandom(1, 1000);
//        if (y <= 500) t = ItemType.SWORD;
//        else t = ItemType.CHEST;
//        return t;
//    }

    static double getAmplifier(ItemQuality whichQuality) {
        if (whichQuality == ItemQuality.NORMAL) {
            return 1;
        } else if (whichQuality == ItemQuality.MAGIC) {
            return 1.3;
        } else if (whichQuality == ItemQuality.RARE) {
            return 1.7;
        } else return 2.3;
    }
}


abstract class UsableItem extends Item {
    UsableItem() {
        super();
    }

    public void useItem() {
        Hero.getInstance().useItem(this);
    }
}

abstract class EquipableItem extends UsableItem {
    ItemType itemType;
    ItemQuality itemQuality;
    double amplifier;
    Map<StatType, Integer> itemStats = new HashMap<>();
//    ArrayList<Stat> itemStats = new ArrayList<>();

    EquipableItem() {
        super();
    }

    private void equipItem() {
        Hero.getInstance().equipItem(this);
    }
}

class Consumable extends UsableItem { //heal potion, exp scroll, item chest(quality/type)
    public Consumable(String name, int cost) {
        super();
    }
}