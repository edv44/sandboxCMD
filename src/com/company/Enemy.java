package com.company;

/**
 * Created by edv44 on 30.01.2017.
 */
class Enemy extends Character {
    public int exp;
    public int gold;
    String[] names = {"Wolf", "Bear", "Boar"};

    public static Enemy generate(int _heroLevel) {
        Enemy enemy = new Enemy();
        enemy.level = Helper.getRandom(_heroLevel, _heroLevel + 3);
        enemy.name = enemy.names[Helper.getRandom(1, enemy.names.length - 1)];
        enemy.calcStats();
        return enemy;
    }

    void calcStats() {
        attack = 16 * level;
        defense = 2 * level;
        hpMax = 44 * level;
        hpCur = hpMax;
        exp = 12 * level;
        gold = Math.round(attack + defense + hpMax) / 3;
    }

    @Override
    protected void targetDefeated() {
        System.out.println("Game over."); //todo: to be done
    }

//    @Override
//    public boolean hit(Character target){ //works fine, released just to test override
//        System.out.println("@overrided Enemy super.hit test");
//        return super.hit(target);
//    }
}