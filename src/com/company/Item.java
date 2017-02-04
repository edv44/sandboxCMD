package com.company;

import sun.util.resources.cldr.ha.CurrencyNames_ha;

import java.util.ArrayList;

/**
 * Created by edv44 on 31.01.2017.
 */
public class Item {
    boolean isEquipped;
    String name;
    //enum name {sword, axe, spear, hammer, dagger};
    //ArrayList<String> name = new ArrayList<String>(){"sword", "axe", "spear", "hammer", "dagger"};
    //String[] name = {"sword", "axe", "spear", "hammer", "dagger"};
    enum type {WEAPON, ARMOR}
    int addHp;
    int addAttack;
    int addDefense;
    int id;

    Item() {
        switch (Helper.getRandom(1, 2)) {
            case 1:
                type WEAPON;
                isEquipped = false;
                //name = "Sword"; //r.next()
                //name = name[Helper.getRandom(0, name.length)];
                id = Helper.getRandom(0, 9999);
                name = "Weapon [" + id + "]";
                addAttack = 4;
                return;
            case 2:
                type ARMOR;
                isEquipped = false;
                id = Helper.getRandom(0, 9999);
                name = "Armor [" + id + "]";;
                addHp = 75;
                addDefense = 2;
                return;
        }
        isEquipped = false;
    }
}
