package com.company;

public class Main {
    static String characterName;
    static int characterClass;

    public static void main(String[] args) {
        characterName = initName();
        characterClass = initClass();
        gameLoop();
    }

    private static String initName() {
        System.out.print("Hello!\nEnter your name: ");
        return Helper.read();
    }

    private static int initClass() {
        System.out.println("Choose your class:\n1 Warrior\n2 Rogue ");
        return Integer.parseInt(Helper.read()); //todo: add mechanism for input validation
    }

    private static void gameLoop() {
        Helper.clearScreen();
        Hero.getInstance().showStats();
        System.out.println("\n\nSelect an action: \n1 Character info.\n2 Search for the enemy.\n3 Inventory.\n4 Go to the town.\n5 Use healing potion [#count#].\n\n");
        switch (Helper.read()) {
            case "1": //1 Character info.
                Hero.getInstance().showFullStats();
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
//            case "97": //for item equip/unequip debug
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