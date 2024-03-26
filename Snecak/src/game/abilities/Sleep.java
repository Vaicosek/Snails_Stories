package game.abilities;

import game.hero.HeroTemplate;
import game.monster.MonsterBase;

public class Sleep implements EntangleAbilityTemplate {
    private static final int SLEEP_DURATION = 2;
    private String name = "Sleep";
    private int totalDamage;
    private int manaCost = 33;
    private boolean unlocked = false;

    public Sleep() {
    }

    @Override
    public void applyEntangle(HeroTemplate hero, MonsterBase monster) {
        monster.setEntangled(true, SLEEP_DURATION);
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

    @Override
    public int getEntangleDuration() {
        return SLEEP_DURATION;
    }

    @Override
    public void setEntangleDuration(int duration) {

    }
}
