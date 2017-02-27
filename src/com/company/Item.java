package com.company;

class Item {
    boolean isEquipped; // by default == false
    String name;
    private itemQuality quality;
    itemType type;
    double addHp;
    double addAttack;
    double addDefense;
    private double amplifier;
    double price;
    private String[] names = {"Axe", "Sword", "Blunt"};

    Item() { //Hero.drop
        quality = getItemQuality();
        type = getItemType();
    }

    Item(itemType _type) { //Hero.goShop.buy
        quality = getItemQuality();
        type = _type;
    }

    private static itemQuality getItemQuality() {
        itemQuality q;
        int x = Helper.getRandom(1, 100);
        if (x <= 50) q = itemQuality.NORMAL;
        else if (x > 50 && x <= 80) q = itemQuality.MAGIC;
        else if (x > 80 && x <= 95) q = itemQuality.RARE;
        else q = itemQuality.EPIC;
        return q;
    }

    private static itemType getItemType() {
        itemType t;
        int y = Helper.getRandom(1, 100);
        if (y <= 50) t = itemType.WEAPON;
        else t = itemType.ARMOR;
        return t;
    }

    static Item generateItem(Integer _enemyLvl, Item _item) {
        switch (_item.quality) {
            case NORMAL:
                _item.amplifier = 1;
                break;
            case MAGIC:
                _item.amplifier = 1.2;
                break;
            case RARE:
                _item.amplifier = 1.5;
                break;
            case EPIC:
                _item.amplifier = 2;
                break;
        }

        switch (_item.type) {
            case WEAPON:
                _item.name = _item.names[Helper.getRandom(0, _item.names.length - 1)] + " [" + Helper.getRandom(0, 9999) + "] [" + _item.quality + "]";
                _item.addAttack = _enemyLvl * _item.amplifier;
                break;
            case ARMOR:
                _item.name = "Chest [" + Helper.getRandom(0, 9999) + "] [" + _item.quality + "]";
                _item.addHp = 25 * _enemyLvl * _item.amplifier;
                _item.addDefense = _enemyLvl * _item.amplifier;
                break;
        }
        _item.price = 50 * _item.amplifier * 5;
        return _item;
    }

    enum itemQuality {
        NORMAL, MAGIC, RARE, EPIC
    }

    enum itemType {
        ARMOR, WEAPON
    }
}