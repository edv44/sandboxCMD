package com.company;

public abstract class Character implements IAttackable {
    String name;
    int hpCur;
    int hpMax;
    int damage;
    int accuracy;
    int defense;
    int level;
    int expCur;
    int gold;

    @Override
    public void hit(Character target) { //todo: add battle log text
        int chanceToHit = 100 * accuracy / (accuracy + target.defense) * 2 * level / (level + target.level);
        if (chanceToHit < 5) chanceToHit = 5;
        else if (chanceToHit > 95) chanceToHit = 95;

        int hit = Helper.getRandom(1, 100);
        if (hit <= chanceToHit) {
            if (damage - target.defense <= 0) damage = 0;
            System.out.printf("\n%s[%s/%s] hits %s[%s/%s] by %s damage, (hit %s%s).", name, hpCur, hpMax, target.name, target.hpCur, target.hpMax, damage, chanceToHit, "%"); //hit info
            target.adjustHealth(-damage);
        } else System.out.printf("\n%s[%s/%s] is missed, (hit %s%s).", name, hpCur, hpMax, chanceToHit, "%");
    }

    private void adjustHealth(int amount) {
        hpCur += amount;
        if (hpCur > hpMax) hpCur = hpMax;
        if (hpCur <= 0) {
            hpCur = 0;
            death();
        }
    }

    protected abstract void death();
}