package com.company;

public class Main {
    static String characterName;
    static int characterClass;

    public static void main(String[] args) {
        initName();
        initClass();
        gameLoop();
    }

    private static void initName() { //todo:add space handler
        Helper.clearScreen();
        System.out.print("Hello!\nEnter your name: ");
        characterName = Helper.read();
    }

    private static void initClass() {
        Helper.clearScreen();
        System.out.print("1 Warrior\n2 Rogue\n\nChoose your class: ");
        switch (Helper.read()) {
            case "1":
                characterClass = 1;
                break;
            case "2":
                characterClass = 2;
                break;
            default:
                initClass();
        }
    }

    private static void gameLoop() {
        Helper.clearScreen();
        Hero.getInstance().showInfo();
        System.out.println("\n\nSelect an action: \n1 Character info.\n2 Search for the enemy.\n3 Inventory.\n4 Go to the town.\n5 Use healing potion [#count#].\n\n");
        switch (Helper.read()) {
            case "1": //1 Character info.
                Hero.getInstance().showFullInfo();
                gameLoop();
            case "2": //2 Search for the enemy. todo: Move to another location (bring the mechanism).
                //todo: hero.location > generate monster from this location
                if (Hero.getInstance().hpCur > 0) {
                    Hero.getInstance().battle(Enemy.generate());
                } else {
                    Helper.clearScreen();
                    System.out.printf("\n%s [0/%s] need some rest, you can restore HP in the town.\n1 To continue.\n", Hero.getInstance().name, Hero.getInstance().hpMax);
                    Helper.read();
                }
                gameLoop();
            case "3": //3 Inventory.
                Hero.getInstance().getInventoryInfo();
                gameLoop();
            case "4": //4 Go to the town (rest, shop, blacksmith, etc).
                Hero.getInstance().goTown();
                gameLoop();
            case "5": //5 Use healing potion [#count#].
                //todo: use heal potion
                gameLoop();
//            case "95": //95 test adding stats on equip/unequip
//                Hero.getInstance().someMethod();
//            case "96": //96 test adding stats on equip/unequip
//                Hero.getInstance().add10Items();
//                System.out.println("\nAdded 10 items to inventory.");
//            case "97": //97 for item equip/unequip debug
//                for (int i = 0; i <= 15; i++) Hero.getInstance().inventory.add(Hero.generateRandomEquipableItem());
//                Hero.getInstance().getInventoryInfo();
//            case "98": //98 test equip > hashMap
//                for (int i = 0; i <= 150; i++) Hero.getInstance().equipItem(Hero.generateRandomEquipableItem());
//            case "99": //99 test Gear class
//                for (int i = 0; i <= 150; i++) Hero.generateRandomEquipableItem();
            default:
                System.out.println("Please type valid number.");
                gameLoop();
        }
    }
}