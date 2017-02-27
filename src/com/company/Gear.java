package com.company;

enum slot {
    weapon, helmet, chest
}

class Gear {
    String name;
    int cost;

    Gear() {
    }
}


abstract class UsableItem extends Gear {
    UsableItem() {
        super();
    }

    public void useItem(Hero _hero) {
        _hero.useItem(this);
    }
}

abstract class EquipableItem extends UsableItem {
    slot whichSlot;

    EquipableItem() {
        super();
    }

    private void equipItem(Hero _hero) {
        _hero.equipItem(this);
    }

}

class Consumable extends UsableItem { //heal potion, exp scroll, item chest
    public Consumable(String _name, int _cost) {
        super();
    }
}

class Weapon extends EquipableItem {
    private int damage;

    Weapon(Hero _hero) {
        super();
        damage = 15 * _hero.level;
        name = Helper.namesWeapon[Helper.getRandom(0, Helper.namesWeapon.length - 1)];
        cost = damage * 3;
        whichSlot = slot.weapon;
    }
}

abstract class Armor extends EquipableItem {
    public int defense;

    public Armor() {
        super();
    }
}

class Chest extends Armor {
    private int defense;

    public Chest(Hero _hero) {
        super();
        defense = 3 * _hero.level;
        name = Helper.namesChest[Helper.getRandom(0, Helper.namesChest.length)];
        cost = defense * 4;
        whichSlot = slot.chest;
    }
}
