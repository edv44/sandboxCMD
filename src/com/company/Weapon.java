package com.company;

abstract class Weapon extends EquipableItem {
    int damage;

    Weapon() {
        super();
        whichType = ItemType.WEAPON;
    }
}

class Sword extends Weapon {

    Sword() {
        super();

        int roll = Helper.getRandom(1, Hero.getInstance().level);//todo: roll 1..(enemy.lvl - hero.lvl)

        switch (roll) {
            case 1:
            case 2:
                name = "Short Sword";
                break;
            case 3:
            case 4:
                name = "Scimitar";
                break;
            case 5:
            case 6:
                name = "Sabre";
                break;
            case 7:
            case 8:
                name = "Falchion";
                break;
            case 9:
            case 10:
                name = "Crystal Sword";
                break;
            case 11:
            case 12:
                name = "Broad Sword";
                break;
            case 13:
            case 14:
                name = "Long Sword";
                break;
            case 15:
            default:
                name = "War Sword";
                break;
        }
        whichQuality = getItemQuality();
        amplifier = getAmplifier(whichQuality);
        double tmpDamage = amplifier * roll * 3; //костыль
        damage = (int) tmpDamage;
        cost = damage * 4;
        name = "[" + whichQuality.toString() + "] " + name;
    }
}