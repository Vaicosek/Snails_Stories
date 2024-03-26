package game.abilities;

import game.hero.HeroTemplate;
import game.monster.MonsterBase;

public class HolyFire implements NormalAbilityTemplate {

    private static final double[] TIER_MULTIPLIERS = {0.05, 0.08, 0.12, 0.18, 0.25};


    private String name = "HolyFire";
    private int totalDamage;
    private int manaCost = 50;
    private boolean unlocked =false;

    public HolyFire() {

    }

    @Override
    public void cast(HeroTemplate hero, MonsterBase monster) {
        int currentMana = hero.getMana();
        int manaCost = getManaCost(); // Get the mana cost from the superclass

        if (currentMana >= manaCost) {
            hero.setMana(currentMana - manaCost);
            System.out.println("Used " + getName() + "!");
            int damage = calculateDamage(monster);
            setDamage(damage);


        } else {
            System.out.println("Not enough mana to use " + getName() + " or it's not your turn.");
        }
    }


    private int calculateDamage(MonsterBase monster) {
        int maxHP = monster.getHP(); // Assuming you have a method to get the max HP of a monster
        int tier = monster.getTier();
        double multiplier = TIER_MULTIPLIERS[tier - 1]; // Get the multiplier based on the monster's tier
        return (int) (maxHP * multiplier) + (5* monster.getTier());
    }

    @Override
    public int getDamage() {
        return totalDamage;
    }

    @Override
    public void setDamage(int totalDamage) {

    }

    @Override
    public int getManaCost() {
        return manaCost;
    }

    @Override
    public void setManaCost(int manaCost) {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isUnlocked() {
        return unlocked;
    }

    @Override
    public void setUnlocked() {
        unlocked = true;
    }
}
