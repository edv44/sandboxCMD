package com.company;

abstract class Armor extends EquipableItem {

    Armor() {
        super();
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

        itemType = ItemType.CHEST;
        itemQuality = getItemQuality();
        itemStats.put(StatType.DEFENSE, roll);
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
                System.out.println("\n\n!!!Chest()!!!\n\n");
        }
    }
}