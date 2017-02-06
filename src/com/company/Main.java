package com.company;

public class Main {

    public static void main(String[] args) {
        String characterName = initName();
        String characterClass = initClass();
        Hero hero = new Hero(characterName, characterClass);
        gameLoop(hero);
    }

    private static String initName() {
        System.out.print("Hello!\nEnter your name: ");
        return Helper.read();
    }

    private static String initClass() {
        System.out.println("Choose your class:\n1 Warrior\n2 Rogue ");
        return Helper.read(); //todo: add mechanism for input validation
    }

    private static void gameLoop(Hero hero) {
        Helper.clearScreen();
        hero.showStats();
        System.out.println("\n\nSelect an action: \n1 Character info.\n2 Search for the enemy.\n3 Inventory.\n4 Move to another location.\n5 Use healing potion [#count#].\n\n");
        String select = Helper.read();
        switch (select) {
            case "1": //1 Character info.
                hero.showFullStats();
                gameLoop(hero);
            case "2": //2 Search for the enemy.
                //todo: hero.location > generate monster from this location
                if (hero.characterCurHp > 0) {
                    Enemy enemy = new Enemy(hero.characterLevel);
                    hero.battle(hero, enemy);
                } else hero.goTown(hero);
                gameLoop(hero);
            case "3": //3 Inventory.
                hero.getInventory();
                gameLoop(hero);
            case "4": //4 Move to another location.
                //todo: bring the mechanism
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