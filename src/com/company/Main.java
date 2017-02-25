package com.company;

public class Main {

    public static void main(String[] args) {
        String characterName = initName();
        int characterClass = initClass();
        Hero hero = new Hero(characterName, characterClass);
        gameLoop(hero);
    }

    private static String initName() {
        System.out.print("Hello!\nEnter your name: ");
        return Helper.read();
    }

    private static int initClass() {
        System.out.println("Choose your class:\n1 Warrior\n2 Rogue ");
        return Integer.parseInt(Helper.read()); //todo: add mechanism for input validation
    }

    private static void gameLoop(Hero hero) {
        Helper.clearScreen();
        hero.showStats();
        System.out.println("\n\nSelect an action: \n1 Character info.\n2 Search for the enemy.\n3 Inventory.\n4 Go to the town.\n5 Use healing potion [#count#].\n\n");
        switch (Helper.read()) {
            case "1": //1 Character info.
                hero.showFullStats();
                gameLoop(hero);
            case "2": //2 Search for the enemy. todo: Move to another location (bring the mechanism).
                //todo: hero.location > generate monster from this location
                if (hero.hpCur > 0) {
                    //hero.battle(hero, Enemy.generate(hero.level));
                    hero.battle(Enemy.generate(hero.level));
                } else {
                    Helper.clearScreen();
                    System.out.printf("\n%s [0/%s] need some rest, you can restore HP in the town.\n1 To continue.\n", hero.name, hero.hpMax);
                    Helper.read();
                }
                gameLoop(hero);
            case "3": //3 Inventory.
                hero.getInventory();
                gameLoop(hero);
            case "4": //4 Go to the town (rest, shop, blacksmith, etc).
                hero.goTown(hero);
                gameLoop(hero);
            case "5": //5 Use healing potion [#count#].
                //todo: use heal potion
                gameLoop(hero);
            default:
                System.out.println("Please type valid number.");
                gameLoop(hero);
        }
    }
}