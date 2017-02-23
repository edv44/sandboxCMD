package com.company;

/**
 * Created by edv44 on 31.01.2017.
 */
public class Item {
    boolean isEquipped; // by default == false
    String name;
    Helper.itemQuality quality;
    Helper.itemType type;
    double addHp;
    double addAttack;
    double addDefense;
    double amplifier;
    double price;
    String[] names = {"Axe", "Sword", "Blunt"};

    Item(Helper.itemQuality _quality, Helper.itemType _type) {
        quality = _quality;
        type = _type;
    }
}