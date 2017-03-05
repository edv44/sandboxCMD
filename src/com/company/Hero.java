package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Hero extends Character {
    private int expMax;
    private int strength;
    private int agility;
    private int vitality;
    private int intellect;
    int enemyCounter;
    private int statPoints;
    private int skillPoints;
    private ArrayList<Item> inventory = new ArrayList<>();
    private Map<ItemType, EquipableItem> equipped = new HashMap<>();
//    private Map<StatType, Integer> heroStats = new HashMap<>();
//    private Map<Stat, Integer> heroStats = new HashMap<>();
    private ArrayList<Stat> heroStats;


    private HeroClass heroClass;
    private static Hero instance;

    static Hero getInstance() {
        if (instance == null) {
            instance = new Hero();
        }
        return instance;
    }

    private Hero() {
        instance = this;
        name = Main.characterName;
        level = 1;
        expCur = 0;
        expMax = 180;

        switch (Main.characterClass) {
            case 1:
                heroClass = new ClassWarrior() {
                };
                break;
            case 2:
                heroClass = new ClassRogue() {
                };
                break;
        }

        applyClass();
        add10Items();//todo:delete after test
    }

    private void applyClass() {
        initStats();
        this.hpMax = heroClass.hpMax;
        this.hpCur = this.hpMax;
        this.attack = heroClass.attack;
        this.defense = heroClass.defense;
        this.statPoints = 0;
        this.skillPoints = 0;
    }

    private void initStats() {
        heroStats = heroClass.stats;
        calcStats();
    }

    private void showHeroStats() { //show hero stats
//        System.out.printf("\nSTR: %s | AGI: %s | VIT: %s | INT: %s.", heroStats.get(StatType.STRENGTH), heroStats.get(StatType.AGILITY), heroStats.get(StatType.VITALITY), heroStats.get(StatType.INTELLECT));
        System.out.printf("\nSTR: %s | AGI: %s | VIT: %s | INT: %s.", strength, agility, vitality, intellect);
    }

    @Override
    protected void death() {
        System.out.println("\n\nGAME OVER."); //todo:TBD
        System.exit(0);
    }

    void showInfo() {
        System.out.printf("\n[%s | level %s | %s/%s hp] %s.", heroClass.name, level, hpCur, hpMax, name);
        showHeroStats();
    }

    void showFullInfo() {
        System.out.printf("\nName: %s.\nLevel: %s (%s/%s).\nClass: %s.\n\nHP: %s/%s.\nAttack: %s.\nDefense: %s.\n\nStat points: %s\nSkill points: %s\n\nGold: %s.\nDefeated monsters: %s.\n\n1 To continue.\n", name, level, expCur, expMax, heroClass.name, hpCur, hpMax, attack, defense, statPoints, skillPoints, gold, enemyCounter);
        showHeroStats();
        Helper.read();
    }

    void battle(Enemy enemy) {
        System.out.printf("Battle between %s[%s] and %s has been stated.", name, level, enemy.name);
        int round = 1;
        while (this.hpCur > 0 && enemy.hpCur > 0) {
            Helper.threadSleep(500);
            System.out.printf("\n\nRound %s.\n", round++);
//            if (hit(enemy) || enemy.hit(this)) break; //return boolean
            hit(enemy);                                    //очень убого выглядит
            if (enemy.hpCur > 0) enemy.hit(this); //это условие проверяется дважды, тут и в цикле
        }
        System.out.println("\n1 To continue.");
        Helper.read();
    }

    void goTown() {
        Helper.clearScreen();
        System.out.printf("You have come into the town and now you're standing on the central square.\nWhere do you want to go?\n\n1 Home to have some rest.\n2 Shop.\n3 Blacksmith.\n4 Academy.\n5 Tavern.\n6 Leave the town.\n");
        String select = Helper.read();
        switch (select) {
            case "1": //Home (restore HP)
                goHome();
                goTown();
                break;
            case "2": //Shop
                goShop();
                goTown();
                break;
            case "3": //Blacksmith
                goBlacksmith();
                goTown();
                break;
            case "4": //Academy
//                goAcademy();
                goTown();
                break;
            case "5": //Academy
                goTavern();
                goTown();
                break;
            case "6": //Leave town
                break;
            default:
                goTown();
        }
    }

    private void goHome() {
        Helper.clearScreen();
        while (hpCur < hpMax) {
            hpCur++;
            System.out.printf("%s [%s/%s] is resting.", name, hpCur, hpMax);
            Helper.threadSleep(100);
            Helper.clearScreen(); //this method to clear single string in the command line is suck
        }
    }

    private void goShop() { //todo:TBD
        Helper.clearScreen();
        System.out.printf("You have entered into the store. The seller greets you.\nWhat do you want to do?\n\n1 Buy weapon.\n2 Buy armor.\n3 Sell.\n");
        switch (Helper.read()) {
            case "1": //Buy weapon
                Helper.clearScreen();
                ArrayList<Item> shopList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    EquipableItem item = new Sword();
                    shopList.add(item);
                    System.out.println((i + 1) + " " + shopList.get(i).name + " : $" + item.cost * 4); //todo: Add mechanism to generate item price
                }
                System.out.println("\nWhich one do you want to buy? [gold: " + gold + "]\n");
                int itemToBuy = Integer.parseInt(Helper.read()) - 1; //todo: Add dumb protection (non numerical symbols)
                if (itemToBuy >= 0 && itemToBuy < 10) {
                    if (gold >= shopList.get(itemToBuy).cost * 4) {
                        inventory.add(shopList.get(itemToBuy));
                        gold -= shopList.get(itemToBuy).cost * 4;
                        System.out.printf("\nYou bought %s for $%s\n1 To continue.\n", shopList.get(itemToBuy).name, shopList.get(itemToBuy).cost * 4);
                        shopList.remove(itemToBuy);
                    } else System.out.println("You don't have enough gold.");
                } else System.out.println("Please enter valid value.");
                Helper.read();
                break;
            case "2": //Buy armor
                break;
            case "3": //Sell
                break;
        }
    } //todo:TBD

//    private void goAcademy() {
//        Helper.clearScreen();
//        System.out.println("You comes to the academy.\n1 Teacher.\n2 Return to the town.\n");
//        switch (Helper.read()) {
//            case "1":
//                Helper.clearScreen();
//                System.out.printf("You have %s skill and %s stat points.\n\n1 To spend stat points.\n2 To spend skill points\n3 Leave teacher.\n\n", statPoints, skillPoints);
//                switch (Helper.read()) {
//                    case "1":
//                        if (statPoints > 0) spendStatPoints();
//                        System.out.println("\nYou have no stat points. Come back when you grow your level.\n\n1 To resume.");
//                        Helper.read();
//                        goAcademy();
//                    case "2":
//                        if (skillPoints > 0) spendSkillPoints();
//                        Helper.read();
//                        goAcademy();
//                    case "3":
//                        break;
//
//                }
//                break;
//            case "2":
//                goTown();
//            default:
//                goShop();
//        }
//    } //todo:TBD

    private void goTavern() {

    } //todo:TBD

    private void goBlacksmith() {

    } //todo:TBD

//    private void spendStatPoints() {
//        System.out.printf("\n%s's stat points: %s\n1 To STR up.\n2 To AGI up.\n3 To VIT up.\n4 To INT up.\n5 Return back.\n", name, statPoints);
//        switch (Helper.read()) {
//            case "1":
//                strength++;
//                statPoints--;
//                System.out.printf("%s's STR: %s\n\n", name, strength);
//                if (statPoints > 0) {
//                    spendStatPoints();
//                }
//                break; //wtf
//            case "2":
//                agility++;
//                statPoints--;
//                System.out.printf("%s's AGI: %s\n\n", name, agility);
//                if (statPoints > 0) {
//                    spendStatPoints();
//                }
//                break;
//            case "3":
//                vitality++;
//                statPoints--;
//                System.out.printf("%s's VIT: %s\n\n", name, vitality);
//                if (statPoints > 0) {
//                    spendStatPoints();
//                }
//                break;
//            case "4":
//                intellect++;
//                statPoints--;
//                System.out.printf("%s's INT: %s\n\n", name, intellect);
//                if (statPoints > 0) {
//                    spendStatPoints();
//                }
//                break;
//            case "5":
//                break;
//            default:
//                spendStatPoints();
//        }
//    } //todo:TBD
//
//    private void spendSkillPoints() { //todo:TBD
//    }

    void lvlUp(Character target) {
        expCur += target.expCur;
        if (expCur >= expMax) {
            level++;
            expCur -= expMax;
            expMax += 100 + expMax * 0.1;
            statPoints += 5;
            skillPoints += 1;
            System.out.printf("\nCongratulations! %s reached level %s.\nNow you have free %s skill and %s stat points.\n", name, level, skillPoints, statPoints);
        }
    } //move > enemy class //todo:make separate class getExp

    void drop(Character target) {
        String i1 = "", i2 = "", i3 = "";
        int getGold = target.gold;
        gold += getGold;
//        drop resources 1-2 todo:TBD
        int r = Helper.getRandom(0, 999);
        if (r < 600) r = 1;
        else if (r >= 600 && r < 850) r = 2;
        else if (r >= 850 && r < 950) r = 3;
        else r = 4;

        switch (r) {
            case 1: // 60% - consumable; todo:TBD
                break;
            case 2: // 25% - item;
                inventory.add(generateRandomEquipableItem());
                i1 = ", " + inventory.get(inventory.size() - 1).name;
                break;
            case 3: // 10% - item + item;
                inventory.add(generateRandomEquipableItem());
                i1 = ", " + inventory.get(inventory.size() - 1).name;
                inventory.add(generateRandomEquipableItem());
                i2 = ", " + inventory.get(inventory.size() - 1).name;
                break;
            case 4: // 5% - item + item + item;
                inventory.add(generateRandomEquipableItem());
                i1 = ", " + inventory.get(inventory.size() - 1).name;
                inventory.add(generateRandomEquipableItem());
                i2 = ", " + inventory.get(inventory.size() - 1).name;
                inventory.add(generateRandomEquipableItem());
                i3 = ", " + inventory.get(inventory.size() - 1).name;
                break;
        }
        System.out.printf("\nEarned: %s exp, %s gold%s%s%s.\n", target.expCur, getGold, i1, i2, i3); //win info
    } //move > enemy class?

    void useItem(UsableItem item) { //use item from inventory
    }

    private void getEquipped() {
        if (equipped.get(ItemType.WEAPON) != null) System.out.println("Weapon: " + equipped.get(ItemType.WEAPON).name);
        if (equipped.get(ItemType.CHEST) != null) System.out.println("Chest: " + equipped.get(ItemType.CHEST).name);
    }

    private void getInventory() {
        for (int i = 0; i < inventory.size(); i++) System.out.println(i + 1 + ": " + inventory.get(i).name + ".");
    }

    void getInventoryInfo() {
        System.out.println("\nEquipped:");
        getEquipped();
        System.out.println("\nInventory:");
        getInventory();
        System.out.println("\n\nEnter item number to equip it or 'q' to return.");
        String chose = Helper.read();
        try {
            Item item = Hero.getInstance().inventory.get(Integer.parseInt(chose) - 1);
            equipItem((EquipableItem) item);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static EquipableItem generateRandomEquipableItem() {
        EquipableItem newItem = null;
        switch (Helper.getRandom(1, 2)) {
            case 1:
                newItem = new Sword();
                break;
            case 2:
                newItem = new Chest();
                break;
            default:
                System.out.println("\n\nERROR: Hero.generateRandomEquipableItem throws some error.\n\n");
        }
        return newItem;
    }

    private void add10Items() { //todo:delete after tests
        for (int i = 0; i < 10; i++) {
            inventory.add(generateRandomEquipableItem());
        }
    }


    /*Item logic v2.1 (using Stat class)*/

        void addItem() { //for further use
        //add item to inventory
    }

    private void calcStats() {
        for (Stat stat : heroStats) {
            stat.calc();
        }
        strength = heroStats.get(0).modValue; //todo:dynamic reference
        agility = heroStats.get(1).modValue;
        vitality = heroStats.get(2).modValue;
        intellect = heroStats.get(3).modValue;
    }

    void equipItem(EquipableItem item) { //equip item from inventory
        if (equipped.containsKey(item.itemType)) {
            unequipItem(item);
        }
        equipped.put(item.itemType, item); //add equipped item to equipped list
        inventory.remove(item); //remove equipped item from inventory list
//        method 1 - each heroStats.type compares with each item.itemStats, call Stat.addMod on match
//        for (Stat stat : heroStats) {
//            if (item.itemStats.containsKey(stat.type)) {
//                stat.addMod(item);
//            }
//        }
//        method 2 - each item.itemStats.StatType compares with each heroStats.Stat.type, call Stat.addMod on match
        for (Map.Entry<StatType, Integer> item1 : item.itemStats.entrySet()) {
            for (Stat stat : heroStats) {
                if (stat.type.toString().equals(item1.getKey().toString())) {
                    stat.addMod(item);
                }
            }
        }
        calcStats();
    }

    private void unequipItem(EquipableItem item) { //unequip item to inventory
        for (Map.Entry<StatType, Integer> item1 : equipped.get(item.itemType).itemStats.entrySet()) {
            for (Stat stat : heroStats) {
                if (stat.type.toString().equals(item1.getKey().toString())) {
                    stat.removeMod(equipped.get(item.itemType));
                }
            }
        }
        inventory.add(equipped.get(item.itemType)); //add unequipped item to inventory list
        equipped.remove(item.itemType); //remove unequipped item from equipped list
    }

    /*Item logic v2 - work's fine (not using Stat class)

    void equipItem(EquipableItem item) { //equip item from inventory
        if (equipped.containsKey(item.itemType)) {
            unequipItem(item);
//            System.out.print("\nunequipped: "); //todo:delete after debug
//            System.out.printf("STR: %s | AGI: %s | VIT: %s | INT: %s.", heroStats.get(StatType.STRENGTH), heroStats.get(StatType.AGILITY), heroStats.get(StatType.VITALITY), heroStats.get(StatType.INTELLECT)); //todo:delete after debug
        }
        equipped.put(item.itemType, item); //add equipped item to equipped list
        inventory.remove(item); //remove equipped item from inventory list
        item.itemStats.forEach((k, v) -> heroStats.put(k, heroStats.get(k) + v)); //add equipped item stats to hero stats
//        System.out.print("\nequipped: "); //todo:delete after debug
//        System.out.printf("STR: %s | AGI: %s | VIT: %s | INT: %s.", heroStats.get(StatType.STRENGTH), heroStats.get(StatType.AGILITY), heroStats.get(StatType.VITALITY), heroStats.get(StatType.INTELLECT)); //todo:delete after debug
    }

    private void unequipItem(EquipableItem item) { //unequip item to inventory
        equipped.get(item.itemType).itemStats.forEach((k, v) -> heroStats.put(k, heroStats.get(k) - equipped.get(item.itemType).itemStats.get(k))); //remove unequipped item stats from hero stats
        inventory.add(equipped.get(item.itemType)); //add unequipped item to inventory list
        equipped.remove(item.itemType); //remove unequipped item from equipped list
    }*/
}