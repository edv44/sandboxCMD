package com.company;

class Enemy extends Character {
    private String[] names = {"Wolf", "Bear", "Boar"};

    static Enemy generate(int _heroLevel) {
        Enemy enemy = new Enemy();
        enemy.level = Helper.getRandom(_heroLevel, _heroLevel + 2);
        enemy.name = enemy.names[Helper.getRandom(1, enemy.names.length - 1)];
        enemy.calcStats();
        return enemy;
    }

    private void calcStats() {
        attack = 11 * level;
        defense = level;
        hpMax = 44 * level;
        hpCur = hpMax;
        expCur = 12 * level;
        gold = Math.round(attack + defense + hpMax) / 3;
    }

    @Override
    protected void targetDefeated(Character _target) {
        System.out.println("\n\nGAME OVER."); //todo: to be done
        System.exit(0);
    }

//    @Override
//    public boolean hit(Character target){ //works fine, released just to test override
//        System.out.println("@overrided Enemy super.hit test");
//        return super.hit(target);
//    }
}