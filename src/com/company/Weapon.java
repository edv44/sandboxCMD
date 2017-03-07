package com.company;

abstract class Weapon extends EquipableItem {

    Weapon() {
        super();
        itemType = ItemType.WEAPON;
        primaryBonuses.add(StatType.STRENGTH); //todo:new object shouldn't have this list in the parameters
        primaryBonuses.add(StatType.AGILITY);
        primaryBonuses.add(StatType.VITALITY);
        primaryBonuses.add(StatType.INTELLECT);
    }

    String getPrimaryStat(int roll) {
        StatType s = primaryBonuses.get(Helper.getRandom(0, primaryBonuses.size() - 1));
        itemStats.put(s, roll);
        primaryBonuses.remove(s);
        return String.format(" %s: %s", s.name().toUpperCase().substring(0, 3), roll);
    }
}

class Sword extends Weapon {

    Sword() {
        super();

        int roll = Helper.getRandom(1, Hero.getInstance().level); //todo: roll 1..(enemy.lvl - hero.lvl)

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

        itemQuality = getItemQuality();
        itemStats.put(StatType.DAMAGE, roll);
        name = String.format("[%s] %s", itemQuality, name);

        switch (itemQuality) {
            case NORMAL:
                cost = roll * 30;
                break;
            case MAGIC:
                name = name + getPrimaryStat(roll);
                cost = roll * 60;
                break;
            case RARE:
                name = name + getPrimaryStat(roll) + getPrimaryStat(roll);
                cost = roll * 90;
                break;
            case EPIC:
                name = name + getPrimaryStat(roll) + getPrimaryStat(roll) + getPrimaryStat(roll);
                cost = roll * 120;
                break;
            default:
                System.out.println("\n\n!!!Sword()!!!\n\n");
        }
    }
}