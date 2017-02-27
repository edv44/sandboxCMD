package com.company;

class Enemy extends Character {
    private String[] names = {"Wolf", "Bear", "Boar"};

    static Enemy generate() {
        Enemy enemy = new Enemy();
        enemy.level = Hero.getInstance().level;
        enemy.name = enemy.names[Helper.getRandom(1, enemy.names.length - 1)];
        enemy.calcStats();
        return enemy;
    }

    private void calcStats() {
        attack = 11 * level;
        defense = level;
        hpMax = 38 * level;
        hpCur = hpMax;
        expCur = defense + Math.round(attack + hpMax) / 2;
        gold = (int) ((defense + Math.round(attack + hpMax) / 2) * 1.15);
    }

    @Override
    protected void death() {
        System.out.printf("\n\n%s defeated %s!", Hero.getInstance().name, name);
        Hero.getInstance().enemyCounter++;
        Hero.getInstance().drop(this);
        Hero.getInstance().lvlUp(this);
    }

//    @Override
//    public boolean hit(Character target){ //works fine, released just to test override
//        System.out.println("@overrided Enemy super.hit test");
//        return super.hit(target);
//    }
}