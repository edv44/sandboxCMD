package com.company;

public abstract class Character implements IAttackable {
    String name;
    int hpCur;
    int hpMax;
    int attack;
    int defense;
    int level;
    int expCur;
    int gold;

    @Override
    public void hit(Character _target) { //todo: add battle log text
        int damage = attack - _target.defense;
        if (damage <= 0) damage = 0;
        System.out.printf("\n%s[%s/%s] hits %s[%s/%s] by %s damage.", name, hpCur, hpMax, _target.name, _target.hpCur, _target.hpMax, damage); //hit info
        _target.adjustHealth(-damage);
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