package com.company;

/**
 * Created by edv44 on 23.02.2017.
 */
public abstract class Character implements IAttackable {
    protected String name;
    public int hpCur;
    public int hpMax;
    public int attack;
    public int defense;
    public int level;

    @Override
    public boolean hit(Character target) { //todo: add battle log text
        System.out.printf("\n%s attacks %s.\n", name, target.name); //need more details
        int damage = attack - target.defense;
        if (damage <= 0) damage = 0;
        target.hpCur -= damage;
        if (target.hpCur <= 0) {
            targetDefeated();
            return true;
        }
        return false;
    }

    protected abstract void targetDefeated();
}
