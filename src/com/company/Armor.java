package com.company;

abstract class Armor extends EquipableItem {
    int defense;

    Armor() {
        super();
    }
}

class Chest extends Armor {

    Chest() {
        super();

        int roll = Helper.getRandom(1, Hero.getInstance().level);//todo: roll 1..(enemy.lvl - hero.lvl)

        switch (roll) {
            case 1:
                name = "Quilted Armor";
                break;
            case 2:
                name = "Leather armor";
                break;
            case 3:
                name = "Hard Leather Armor";
                break;
            case 4:
                name = "Studded Leather";
                break;
            case 5:
                name = "Ring Mail";
                break;
            case 6:
                name = "Scale Mail";
                break;
            case 7:
                name = "Breast Plate";
                break;
            case 8:
                name = "Chain Mail";
                break;
            case 9:
                name = "Splint Mail";
                break;
            case 10:
                name = "Light Plate";
                break;
            case 11:
                name = "Field Plate";
                break;
            case 12:
                name = "Plate Mail";
                break;
            case 13:
                name = "Gothic Plate";
                break;
            case 14:
                name = "Full Plate Mail";
                break;
            case 15:
            default:
                name = "Ancient Armor";
                break;
        }
        whichType = ItemType.CHEST;
        whichQuality = getItemQuality();
        amplifier = getAmplifier(whichQuality);
        double tmpDefense = amplifier * roll * 3; //костыль
        defense = (int) tmpDefense;
        cost = defense * 4;
        name = "[" + whichQuality.toString() + "] " + name;
    }
}