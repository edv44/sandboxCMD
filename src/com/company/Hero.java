package com.company;

import java.util.ArrayList;

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
    private ArrayList<Gear> inventoryGear = new ArrayList<>(); //todo: just for Gear class debug
    private boolean isWeaponEquipped = false;
    private boolean isArmorEquipped = false;

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

    @Override
    protected void death() {
        System.out.println("\n\nGAME OVER."); //todo:TBD
        System.exit(0);
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
        while (this.hpCur > 0 && _enemy.hpCur > 0) {
            Helper.threadSleep(500);
            System.out.printf("\n\nRound %s.\n", round++);
//            if (hit(_enemy) || _enemy.hit(this)) break;
            hit(_enemy);                                    //очень убого выглядит
            if (_enemy.hpCur > 0) _enemy.hit(this); //это условие проверяется дважды, тут и в цикле
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
                goAcademy();
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

    private void goShop() {
        Helper.clearScreen();
        System.out.printf("You have entered into the store. The seller greets you.\nWhat do you want to do?\n\n1 Buy weapon.\n2 Buy armor.\n3 Sell.\n");
        switch (Helper.read()) {
            case "1": //Buy weapon
                Helper.clearScreen();
                System.out.println("Which one do you want to buy?\n");
                ArrayList<Item> shopList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    Item item = new Item(Item.itemType.WEAPON);
                    shopList.add(Item.generateItem(level, item));
                    System.out.println((i + 1) + " " + shopList.get(i).name + " : $" + item.price); //todo: Add mechanism to generate item price
                }
                int itemToBuy = Integer.parseInt(Helper.read()) - 1; //todo: Add dumb protection (non numerical symbols)
                if (itemToBuy >= 0 && itemToBuy < 10) { //todo: Add available gold check
                    inventory.add(shopList.get(itemToBuy)); //todo: _hero.gold -= item.cost
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

    private void goAcademy() {
        Helper.clearScreen();
        System.out.println("You comes to the academy.\n1 Teacher.\n2 Return to the town.\n");
        switch (Helper.read()) {
            case "1":
                Helper.clearScreen();
                System.out.printf("You have %s skill and %s stat points.\n\n1 To spend stat points.\n2 To spend skill points\n3 Leave teacher.\n\n", statPoints, skillPoints);
                switch (Helper.read()) {
                    case "1":
                        if (statPoints > 0) spendStatPoints();
                        System.out.println("\nYou have no stat points. Come back when you grow your level.\n\n1 To resume.");
                        Helper.read();
                        goAcademy();
                    case "2":
                        if (skillPoints > 0) spendSkillPoints();
                        Helper.read();
                        goAcademy();
                    case "3":
                        break;

                }
                break;
            case "2":
                goTown();
            default:
                goShop();
        }
    } //todo:TBD

    private void goTavern() {

    } //todo:TBD

    private void goBlacksmith() {

    } //todo:TBD

    private void spendStatPoints() {
        System.out.printf("\n%s's stat points: %s\n1 To STR up.\n2 To AGI up.\n3 To VIT up.\n4 To INT up.\n5 Return back.\n", name, statPoints);
        switch (Helper.read()) {
            case "1":
                strength++;
                statPoints--;
                System.out.printf("%s's STR: %s\n\n", name, strength);
                if (statPoints > 0) {
                    spendStatPoints();
                }
                break; //wtf
            case "2":
                agility++;
                statPoints--;
                System.out.printf("%s's AGI: %s\n\n", name, agility);
                if (statPoints > 0) {
                    spendStatPoints();
                }
                break;
            case "3":
                vitality++;
                statPoints--;
                System.out.printf("%s's VIT: %s\n\n", name, vitality);
                if (statPoints > 0) {
                    spendStatPoints();
                }
                break;
            case "4":
                intellect++;
                statPoints--;
                System.out.printf("%s's INT: %s\n\n", name, intellect);
                if (statPoints > 0) {
                    spendStatPoints();
                }
                break;
            case "5":
                break;
            default:
                spendStatPoints();
        }
    } //todo:TBD

    private void spendSkillPoints() { //todo:TBD
    }

    void lvlUp(Character _target) {
        expCur += _target.expCur;
        if (expCur >= expMax) {
            level++;
            expCur -= expMax;
            expMax += 100 + expMax * 0.1;
            statPoints += 5;
            skillPoints += 1;
            System.out.printf("\nCongratulations! %s reached level %s.\nNow you have free %s skill and %s stat points.\n", name, level, skillPoints, statPoints);
        }
    } //move > enemy class

    void drop(Character _target) {
        String i1 = "", i2 = "", i3 = "";
        int getGold = _target.gold;
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
                inventory.add(Item.generateItem(_target.level, new Item()));
                i1 = ", " + inventory.get(inventory.size() - 1).name;
                break;
            case 3: // 10% - item + item;
                inventory.add(Item.generateItem(_target.level, new Item()));
                i1 = ", " + inventory.get(inventory.size() - 1).name;
                inventory.add(Item.generateItem(_target.level, new Item()));
                i2 = ", " + inventory.get(inventory.size() - 1).name;
                break;
            case 4: // 5% - item + item + item;
                inventory.add(Item.generateItem(_target.level, new Item()));
                i1 = ", " + inventory.get(inventory.size() - 1).name;
                inventory.add(Item.generateItem(_target.level, new Item()));
                i2 = ", " + inventory.get(inventory.size() - 1).name;
                inventory.add(Item.generateItem(_target.level, new Item()));
                i3 = ", " + inventory.get(inventory.size() - 1).name;
                break;
        }

        System.out.printf("\nEarned: %s exp, %s gold%s%s%s.\n", _target.expCur, getGold, i1, i2, i3); //win info
    } //move > enemy class?

    /*Item logic v2 - to be done*/

    void addItem() {
        //add item to inventory
        Gear newItem = new Weapon(this);
        inventoryGear.add(newItem);
        System.out.printf("%s has been added to inventory.", newItem.name);
    }

    void equipItem(EquipableItem _item) {
        //equip item from inventory

    }

    void unequipItem(Item _item) {
        //unequip item to inventory
    }

    void useItem(UsableItem _item) {
        //use item from inventory
    }
}