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
        damage = level * Helper.getRandom(10, 13);
        accuracy = level * Helper.getRandom(7, 10);
        defense = level * Helper.getRandom(3, 5);
        hpMax = Helper.getRandom(31, 45) * level;
        hpCur = hpMax;
        expCur = accuracy + defense + Math.round(damage + hpMax) / 2;
        gold = (int) ((defense + Math.round(damage + hpMax) / 2) * 1.15);
    }

    @Override
    protected void death() {
        System.out.printf("\n\n%s defeated %s!", Hero.getInstance().name, name);
        Hero.getInstance().enemyCounter++;
        Hero.getInstance().drop(this);
        Hero.getInstance().expUp(this.expCur);
    }

//    @Override
//    public boolean hit(Character target){ //works fine, released just to test override
//        System.out.println("@override Enemy super.hit test");
//        return super.hit(target);
//    }
}