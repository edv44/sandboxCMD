package com.company;

import java.util.ArrayList;

public class Hero extends Character {
    private int expMax;
    private int strength;
    private int agility;
    private int vitality;
    private int intellect;
    private int enemyCounter;
    private int statPoints;
    private int skillPoints;
    private ArrayList<Item> inventory = new ArrayList<>();
    private boolean isWeaponEquipped = false;
    private boolean isArmorEquipped = false;

    private HeroClass heroClass;

    Hero(String _name, int _heroClass) {
        name = _name;
        level = 1;
        expCur = 0;
        expMax = 180;

        switch (_heroClass) {
            case 2:
                heroClass = new ClassRogue() {
                };
                break;
            case 1:
            default:
                heroClass = new ClassWarrior() { // todo: need mechanism for input validation
                };
        }
        applyClass();
    }

    private void applyClass() {
        this.hpMax = heroClass.hpMax;
        this.hpCur = this.hpMax;
        this.attack = heroClass.attack;
        this.defense = heroClass.defense;
        this.strength = heroClass.strength;
        this.agility = heroClass.agility;
        this.vitality = heroClass.vitality;
        this.intellect = heroClass.intellect;
        this.statPoints = 2; //reset to 0
        this.skillPoints = 10; //reset to 0
    }

    @Override //todo: to be done (log + loot + exp)
    protected void targetDefeated(Character _target) {
        enemyCounter++;
        drop(_target);
        lvlUp(_target);
    }

    void showStats() {
        System.out.printf("\n[%s | level %s | %s/%s hp] %s.", heroClass.name, level, hpCur, hpMax, name);
    }

    void showFullStats() {
        System.out.printf("\nName: %s.\nLevel: %s (%s/%s).\nClass: %s.\n\nHP: %s/%s.\nAttack: %s.\nDefense: %s.\n\nStat points: %s\nSkill points: %s\n\nGold: %s.\nDefeated monsters: %s.\n\n1 To continue.\n", name, level, expCur, expMax, heroClass.name, hpCur, hpMax, attack, defense, statPoints, skillPoints, gold, enemyCounter);
        Helper.read();
    }

    void battle(Enemy _enemy) {
        System.out.printf("Battle between %s[%s] and %s has been stated.", name, level, _enemy.name);
        int round = 1;
        while (true) {
            Helper.threadSleep(500);
            System.out.printf("\n\nRound %s.\n", round++);
            if (hit(_enemy) || _enemy.hit(this)) break;
        }
        System.out.println("\n1 To continue.");
        Helper.read();
    }

    void getInventory() {
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

    private void equip(Item _item) {

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

    void goTown(Hero _hero) {
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

    private void goHome(Hero _hero) {
        Helper.clearScreen();
        while (_hero.hpCur < _hero.hpMax) {
            _hero.hpCur++;
            System.out.printf("%s [%s/%s] is resting.", _hero.name, _hero.hpCur, _hero.hpMax);
            Helper.threadSleep(100);
            Helper.clearScreen(); //this method to clear single string in the command line is suck
        }
    }

    private void goShop(Hero _hero) {
        Helper.clearScreen();
        System.out.printf("You have entered into the store. The seller greets you.\nWhat do you want to do?\n\n1 Buy weapon.\n2 Buy armor.\n3 Sell.\n");
        switch (Helper.read()) {
            case "1": //Buy weapon
                Helper.clearScreen();
                System.out.println("Which one do you want to buy?\n");
                ArrayList<Item> shopList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    Item item = new Item(Item.itemType.WEAPON);
                    shopList.add(Item.generateItem(_hero.level, item));
                    System.out.println((i + 1) + " " + shopList.get(i).name + " : $" + item.price); //todo: Add mechanism to generate item price
                }
                int itemToBuy = Integer.parseInt(Helper.read()) - 1; //todo: Add dumb protection (non numerical symbols)
                if (itemToBuy >= 0 && itemToBuy < 10) { //todo: Add available gold check
                    _hero.inventory.add(shopList.get(itemToBuy)); //todo: _hero.gold -= item.cost
                    System.out.printf("\nYou bought %s for $%s\n1 To continue.\n", shopList.get(itemToBuy).name, shopList.get(itemToBuy).price);
                } else System.out.println("Please enter valid value.");
                Helper.read();
                break;
            case "2": //Buy armor
                break;
            case "3": //Sell
                break;

        }

    } //todo:TBD

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
    } //todo:TBD

    private void goTavern(Hero _hero) {

    } //todo:TBD

    private void goBlacksmith(Hero _hero) {

    } //todo:TBD

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
    } //todo:TBD

    private void spendSkillPoints(Hero _hero) { //todo:TBD
    }

    private void lvlUp(Character _target) {
        expCur += _target.expCur;
        if (expCur >= expMax) {
            level++;
            expCur -= expMax;
            expMax += 100 + expMax * 0.1;
            statPoints += 5;
            skillPoints += 1;
            System.out.printf("\nCongratulations! %s reached level %s.\nNow you have free %s skill and %s stat points.\n", name, level, skillPoints, statPoints);
        }
    }

    private void drop(Character _target) {
        int getGold = _target.gold;
        gold += getGold;
        inventory.add(Item.generateItem(_target.level, new Item()));
        System.out.printf("\nEarned: %s exp, %s gold, %s.\n", _target.expCur, getGold, inventory.get(inventory.size() - 1).name); //win info
    }
}