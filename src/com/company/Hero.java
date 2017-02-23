package com.company;

import java.util.ArrayList;

/**
 * Created by edv44 on 29.01.2017.
 */
public class Hero {
    protected String name;
    private String characterClass;
    public int level;
    public int expCur;
    public int expMax;
    public int hpMax;
    public int hpCur;
    public int attack;
    public int defense;
    public int strength;
    public int agility;
    public int vitality;
    public int intellect;
    public int monsterCounter;
    public int money;
    public int statPoints;
    public int skillPoints;
    public ArrayList<Item> inventory = new ArrayList<>();
    public boolean isWeaponEquipped = false;
    public boolean isArmorEquipped = false;

    public Hero(String _heroName, String _heroClass) {
        name = _heroName;
        level = 1;
        expCur = 0;
        expMax = 100;
        statPoints = 2; //reset to 0
        skillPoints = 10; //reset to 0
        money = 0;

        switch (_heroClass) { //todo: rebalance class initial stats, leave basic stats "str/agi/vit/int" instead of hp/attack/def.
            case "1":
                characterClass = "Warrior";
                hpMax = 100;
                attack = 700; //7
                defense = 7;
                strength = 6;
                agility = 4;
                vitality = 7;
                intellect = 3;
                break;
            case "2":
                characterClass = "Rogue";
                hpMax = 75;
                attack = 10;
                defense = 5;
                strength = 5;
                agility = 6;
                vitality = 5;
                intellect = 4;
                break;
            default:
                System.out.println("Please type valid number.");
                System.exit(2); //add goto switch in case "default"
        }
        hpCur = hpMax;
    }

    public void showStats() {
        System.out.printf("\n[%s | level %s | %s/%s hp] %s.", characterClass, level, hpCur, hpMax, name);
    }

    public void showFullStats() {
        System.out.printf("\nName: %s.\nLevel: %s (%s/%s).\nClass: %s.\n\nHP: %s/%s.\nAttack: %s.\nDefense: %s.\n\nStat points: %s\nSkill points: %s\n\nGold: %s.\nDefeated monsters: %s.\n\n1 To continue.\n", name, level, expCur, expMax, characterClass, hpCur, hpMax, attack, defense, statPoints, skillPoints, money, monsterCounter);
        Helper.read();
    }

    public void battle(Hero _hero, Enemy _enemy) {
        System.out.printf("Battle between %s[%s] and %s has been stated.", _hero.name, _hero.level, _enemy.name);
        int round = 1;
        while (true) {
            Helper.threadSleep(500);
            System.out.printf("\n\nRound %s.\n", round);
            if (Helper.hit(_hero, _enemy) || Helper.hit(_enemy, _hero)) break;
            round++;
        }
        System.out.println("\n1 To continue.");
        Helper.read();
    }

    public void getInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.\n1 To continue.");
            Helper.read();
        } else {
            int i = 1;
            for (Item x : inventory) {
                if (x.isEquipped) {
                    System.out.printf("[%s] %s [equipped].\n", i, x.name);
                } else System.out.printf("[%s] %s.\n", i, x.name);
                i++;
            }
            System.out.println("\n1 To continue.\n2 To equip item.\n");
            switch (Helper.read()) {
                case "1":
                    break;
                case "2":
                    System.out.println("Which item you want to equip?");
                    equip(inventory.get(Helper.in.nextInt() - 1));
                    System.out.println("\n1 To continue.");
                    Helper.read();
                default:
                    break;
            }
        }
    }

    public void equip(Item _item) {

        switch (_item.type) {
            case WEAPON:
                if (!isWeaponEquipped) {
                    isWeaponEquipped = true;
                    equipTrue(_item);
                } else equipFalse(_item);
                break;
            case ARMOR:
                if (!isArmorEquipped) {
                    isArmorEquipped = true;
                    equipTrue(_item);
                } else equipFalse(_item);
                break;
            default:
                System.out.println("Please enter valid number.");
                break;
        }
    }

    private void equipTrue(Item _item) {
        _item.isEquipped = true;
        attack += _item.addAttack;
        defense += _item.addDefense;
        hpMax += _item.addHp;
        hpCur += _item.addHp;
        System.out.println(_item.name + " has been equipped.");
    }

    private void equipFalse(Item _item) {
        System.out.println(_item.type + " slot is not empty.");
    }

    public void goTown(Hero _hero) {
        Helper.clearScreen();
        System.out.printf("You have come into the town and now you're standing on the central square.\nWhere do you want to go?\n\n1 Home to have some rest.\n2 Shop.\n3 Blacksmith.\n4 Academy.\n5 Tavern.\n6 Leave the town.\n");
        String select = Helper.read();
        switch (select) {
            case "1": //Home (restore HP)
                goHome(_hero);
                goTown(_hero);
                break;
            case "2": //Shop
                goShop(_hero);
                goTown(_hero);
                break;
            case "3": //Blacksmith
                goBlacksmith(_hero);
                goTown(_hero);
                break;
            case "4": //Academy
                goAcademy(_hero);
                goTown(_hero);
                break;
            case "5": //Academy
                goTavern(_hero);
                goTown(_hero);
                break;
            case "6": //Leave town
                break;
            default:
                goTown(_hero);
        }
    }


    public void goHome(Hero _hero) {
        Helper.clearScreen();
        while (_hero.hpCur < _hero.hpMax) {
            _hero.hpCur++;
            System.out.printf("%s [%s/%s] is resting.", _hero.name, _hero.hpCur, _hero.hpMax);
            Helper.threadSleep(100);
            Helper.clearScreen(); //this method to clear single string in the command line is suck
        }
    }

    public void goShop(Hero _hero) {
        Helper.clearScreen();
        System.out.printf("You have entered into the store. The seller greets you.\nWhat do you want to do?\n\n1 Buy weapon.\n2 Buy armor.\n3 Sell.\n");
        switch (Helper.read()) {
            case "1": //Buy weapon
                Helper.clearScreen();
                System.out.println("Which one do you want to buy?\n");
                ArrayList<Item> shopList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    Item item = new Item(Helper.getItemQuality(), Helper.itemType.WEAPON);
                    shopList.add(Helper.generateItem(_hero.level, item));
                    System.out.println((i + 1) + " " + shopList.get(i).name + " : $" + item.price); //todo: Add mechanism to generate item price
                }
                int itemToBuy = Integer.parseInt(Helper.read()) - 1; //todo: Add dumb protection (non numerical symbols)
                if (itemToBuy >= 0 && itemToBuy < 10) { //todo: Add available money check
                    _hero.inventory.add(shopList.get(itemToBuy)); //todo: _hero.money -= item.cost
                    System.out.printf("\nYou bought %s for $%s\n1 To continue.\n", shopList.get(itemToBuy).name, shopList.get(itemToBuy).price);
                } else System.out.println("Please enter valid value.");
                Helper.read();
                break;
            case "2": //Buy armor
                break;
            case "3": //Sell
                break;

        }

    }

    private void goAcademy(Hero _hero) {
        Helper.clearScreen();
        System.out.println("You comes to the academy.\n1 Teacher.\n2 Return to the town.\n");
        switch (Helper.read()) {
            case "1":
                Helper.clearScreen();
                System.out.printf("You have %s skill and %s stat points.\n\n1 To spend stat points.\n2 To spend skill points\n3 Leave teacher.\n\n", _hero.statPoints, _hero.skillPoints);
                switch (Helper.read()) {
                    case "1":
                        if (_hero.statPoints > 0) spendStatPoints(_hero);
                        System.out.println("\nYou have no stat points. Come back when you grow your level.\n\n1 To resume.");
                        Helper.read();
                        goAcademy(_hero);
                    case "2":
                        if (_hero.skillPoints > 0) spendSkillPoints(_hero);
                        Helper.read();
                        goAcademy(_hero);
                    case "3":
                        break;

                }
                break;
            case "2":
                goTown(_hero);
            default:
                goShop(_hero);
        }
    }

    public void goTavern(Hero _hero) {

    }

    public void goBlacksmith(Hero _hero) {

    }

    private void spendStatPoints(Hero _hero) {
        System.out.printf("\n%s's stat points: %s\n1 To STR up.\n2 To AGI up.\n3 To VIT up.\n4 To INT up.\n5 Return back.\n", name, statPoints);
        switch (Helper.read()) {
            case "1":
                _hero.strength++;
                _hero.statPoints--;
                System.out.printf("%s's STR: %s\n\n", name, strength);
                if (_hero.statPoints > 0) {
                    spendStatPoints(_hero);
                }
                break; //wtf
            case "2":
                _hero.agility++;
                _hero.statPoints--;
                System.out.printf("%s's AGI: %s\n\n", name, agility);
                if (_hero.statPoints > 0) {
                    spendStatPoints(_hero);
                }
                break;
            case "3":
                _hero.vitality++;
                _hero.statPoints--;
                System.out.printf("%s's VIT: %s\n\n", name, vitality);
                if (_hero.statPoints > 0) {
                    spendStatPoints(_hero);
                }
                break;
            case "4":
                _hero.intellect++;
                _hero.statPoints--;
                System.out.printf("%s's INT: %s\n\n", name, intellect);
                if (_hero.statPoints > 0) {
                    spendStatPoints(_hero);
                }
                break;
            case "5":
                break;
            default:
                spendStatPoints(_hero);
        }
    }

    private void spendSkillPoints(Hero _hero) {
    }
}