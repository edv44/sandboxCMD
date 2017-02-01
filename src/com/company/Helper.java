package com.company;

import java.util.Random;

/**
 * Created by edv44 on 30.01.2017.
 */
public class Helper {
    public static Random r = new Random();

    public static void threadSleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static int getRandom(int min, int max) {
        return r.nextInt((max - min) + 1) + min;
    }

    public static boolean hit(Hero hero, Enemy enemy) {
        int heroDamage = hero.characterAttack - enemy.defense;

        enemy.curHp -= heroDamage;
        System.out.printf("\n%s[%s/%s] hits %s[%s/%s] by %s damage.", hero.characterName, hero.characterCurHp, hero.characterHp, enemy.name, enemy.curHp, enemy.maxHp, heroDamage);
        if (enemy.curHp <= 0) {
            enemy.curHp = 0;
            hero.monsterCounter++;
            hero.characterCurExp += enemy.exp; //lvl up not released
            hero.inventory.add(generateItem());
            System.out.printf("\nWinner: %s.\nEarned: %s exp, %s.", hero.characterName, enemy.exp,hero.inventory.get(hero.inventory.size() - 1).name);
            return true;
        }
        return false;
    }

    public static boolean hit(Enemy enemy, Hero hero) {
        int damage = enemy.attack - hero.characterDefense;

        hero.characterCurHp -= damage;
        System.out.printf("\n%s[%s/%s] hits %s[%s/%s] by %s damage.", enemy.name, enemy.curHp, enemy.maxHp, hero.characterName, hero.characterCurHp, hero.characterHp, damage);
        if (hero.characterCurHp <= 0) {
            hero.characterCurHp = 0;
            System.out.printf("\nWinner: %s.", enemy.name);
            return true;
        }
        return false;
    }

    public static Item generateItem() {
        Item item = new Item();
        return item;
    }

    public static void clearScreen() {
        for(int i = 0; i < 80; i++)//Default Height of cmd is 300 and Default width is 80 todo: autistic method
        System.out.println("\b");
    }

    public static void randomTest() { //just for test rnd(min, max)
        for (int i = 0; i < 100; i++) {
            System.out.println(getRandom(10, 95));
        }
    }
/*
    public enum RandomActionType {
        NEW_CON(24, 48, 33, 100),
        WKP_WITHOUT_TRADE(24, 48, 0, 0),
        CANCEL_CON(24, 0, 33, 0),
        AMEND_CON(24, 0, 30, 0),
        MAKE_WKP(0, 0, 4, 0),
        WKP_WITH_TRADE(4, 4, 0, 0);

        private static final Random rnd = new Random();
        private int hasBoth;
        private int hasWkp;
        private int hasCon;
        private int hasntBoth;

        private RandomActionType(int hasBoth, int hasWkp, int hasCon, int hasntBoth) {
            this.hasBoth = hasBoth;
            this.hasWkp = hasWkp;
            this.hasCon = hasCon;
            this.hasntBoth = hasntBoth;
        }

        public void changeChances(int hasBoth, int hasWkp, int hasCon, int hasntBoth) {
            if(this == WKP_WITHOUT_TRADE) {
                if(hasCon != 0) {
                    throw new IllegalArgumentException("WKP_WITHOUT_TRADE chance always should be zero for \'only_con\' state");
                }

                if(hasntBoth != 0) {
                    throw new IllegalArgumentException("WKP_WITHOUT_TRADE chance always should be zero for \'no_orders\' state");
                }
            }

            if(this == CANCEL_CON) {
                if(hasWkp != 0) {
                    throw new IllegalArgumentException("CANCEL_CON chance always should be zero for \'only_wkp\' state");
                }

                if(hasntBoth != 0) {
                    throw new IllegalArgumentException("CANCEL_CON chance always should be zero for \'no_orders\' state");
                }
            }

            if(this == AMEND_CON) {
                if(hasWkp != 0) {
                    throw new IllegalArgumentException("AMEND_CON chance always should be zero for \'only_wkp\' state");
                }

                if(hasntBoth != 0) {
                    throw new IllegalArgumentException("AMEND_CON chance always should be zero for \'no_orders\' state");
                }
            }

            if(this == MAKE_WKP) {
                if(hasBoth != 0) {
                    throw new IllegalArgumentException("MAKE_WKP chance always should be zero for \'has_both\' state");
                }

                if(hasWkp != 0) {
                    throw new IllegalArgumentException("MAKE_WKP chance always should be zero for \'only_wkp\' state");
                }

                if(hasntBoth != 0) {
                    throw new IllegalArgumentException("MAKE_WKP chance always should be zero for \'no_orders\' state");
                }
            }

            if(this == WKP_WITH_TRADE) {
                if(hasCon != 0) {
                    throw new IllegalArgumentException("WKP_WITH_TRADE chance always should be zero for \'only_con\' state");
                }

                if(hasntBoth != 0) {
                    throw new IllegalArgumentException("WKP_WITH_TRADE chance always should be zero for \'no_orders\' state");
                }
            }

            this.hasBoth = hasBoth;
            this.hasWkp = hasWkp;
            this.hasCon = hasCon;
            this.hasntBoth = hasntBoth;
        }

        private int getPercentage(Boolean inWorkup, boolean hasOrdersInCON) {
            return inWorkup.booleanValue() && hasOrdersInCON?this.hasBoth:(inWorkup.booleanValue()?this.hasWkp:(hasOrdersInCON?this.hasCon:this.hasntBoth));
        }

        public static RandomActionType getRandomAction(Boolean inWorkup, boolean hasOrdersInCON) {
            int hundred = 0;
            RandomActionType[] var6;
            int type = (var6 = values()).length;

            int sum;
            for(sum = 0; sum < type; ++sum) {
                RandomActionType action = var6[sum];
                hundred += action.getPercentage(inWorkup, hasOrdersInCON);
            }

            int var9 = rnd.nextInt(hundred);
            sum = 0;
            RandomActionType[] var8;
            int var7 = (var8 = values()).length;

            for(int var11 = 0; var11 < var7; ++var11) {
                RandomActionType var10 = var8[var11];
                sum += var10.getPercentage(inWorkup, hasOrdersInCON);
                if(var9 < sum) {
                    return var10;
                }
            }

            return NEW_CON;
        }

        public static RandomActionType getRandomActionWithoutTrade(Boolean inWorkup, Boolean hasOrdersInCON) {
            byte hundred = 0;
            int var9 = hundred + NEW_CON.getPercentage(inWorkup, hasOrdersInCON.booleanValue());
            var9 += AMEND_CON.getPercentage(inWorkup, hasOrdersInCON.booleanValue());
            var9 += CANCEL_CON.getPercentage(inWorkup, hasOrdersInCON.booleanValue());
            int action = rnd.nextInt(var9);
            int sum = 0;
            RandomActionType[] var8;
            int var7 = (var8 = values()).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                RandomActionType type = var8[var6];
                sum += type != WKP_WITH_TRADE && type != MAKE_WKP && type != WKP_WITH_TRADE?type.getPercentage(inWorkup, hasOrdersInCON.booleanValue()):0;
                if(action < sum) {
                    return type;
                }
            }

            return hasOrdersInCON.booleanValue() && rnd.nextBoolean()?CANCEL_CON:NEW_CON;
        }
    }*/
}
