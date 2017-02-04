package com.company;

import java.util.ArrayList;

/**
 * Created by edv44 on 29.01.2017.
 */
public class Hero {
    public String characterName;
    public String characterClass;
    public int characterHp;
    public int characterCurHp;
    public int characterAttack;
    public int characterDefense;
    public int characterLevel;
    public int characterCurExp;
    public int characterExpToUp;
    public int monsterCounter;
    public int characterLocation;
    //ArrayList<String> equipped = new ArrayList<>();
    ArrayList<Item> inventory = new ArrayList<>();
    public boolean isWeaponEquipped = false;


    public Hero(String _heroName, String _heroClass) {
        characterName = _heroName;
        characterLevel = 1;
        characterCurExp = 0;
        characterExpToUp = 100;
        monsterCounter = 0;
        characterLocation = 1;

        switch (_heroClass) {
            case "1":
                characterClass = "Warrior";
                characterHp = 100;
                characterAttack = 700; //7
                characterDefense = 7;
                break;
            case "2":
                characterClass = "Rogue";
                characterHp = 75;
                characterAttack = 10;
                characterDefense = 5;
                break;
            default:
                System.out.println("Please type valid number.");
                System.exit(2); //add goto switch in case "default"
        }
        characterCurHp = characterHp;
    }

    public void showStats() {
        System.out.printf("\n[%s | level %s | %s/%s hp] %s.", characterClass, characterLevel, characterCurHp, characterHp, characterName);
    }

    public void showFullStats() {
        System.out.printf("\nName: %s.\nLevel: %s (%s/%s).\nClass: %s.\n\nHP: %s/%s.\nAttack: %s.\nDefense: %s.\n\nDefeated monsters: %s.\n\n1 To continue.\n", characterName, characterLevel, characterCurExp, characterExpToUp, characterClass, characterCurHp, characterHp, characterAttack, characterDefense, monsterCounter);
        Helper.read();
    }

    public void battle(Hero _hero, Enemy _enemy) {
        System.out.printf("Battle between %s[%s] and %s has been stated.", _hero.characterName, _hero.characterLevel, _enemy.name);
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
        if (!isWeaponEquipped) {//all items affect this property; todo: diff construction for each item type
            _item.isEquipped = true;
            isWeaponEquipped = true;
            characterAttack += _item.addAttack;
            characterDefense += _item.addDefense;
            characterHp += _item.addHp;
            characterCurHp += _item.addHp;
            System.out.println(_item.name + " has been equipped.");
        } else {
            //unequip current item and equip this item
            System.out.println("Weapon is already equipped.");
        }
    }
}