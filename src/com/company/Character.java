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
    public boolean hit(Character _target) { //todo: add battle log text
        int damage = attack - _target.defense;
        if (damage <= 0) damage = 0;
        System.out.printf("\n%s[%s/%s] hits %s[%s/%s] by %s damage.", name, hpCur, hpMax, _target.name, _target.hpCur, _target.hpMax, damage); //hit info
        _target.hpCur -= damage;
        if (_target.hpCur <= 0) {
            System.out.printf("\n\n%s won the battle!", name);
            targetDefeated(_target);
            return true;
        }
        return false;
    }

    protected abstract void targetDefeated(Character _target);
}
