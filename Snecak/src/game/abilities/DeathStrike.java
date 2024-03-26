package game.abilities;

import game.hero.HeroTemplate;
import game.monster.MonsterBase;

public class DeathStrike implements NormalAbilityTemplate {

    private String name = "DeathStrike";
    private int totalDamage;
    private int manaCost;
    private boolean unlocked = false;

    public DeathStrike() {

    }
    @Override
    public void cast(HeroTemplate hero, MonsterBase currentMonster) {
        int lowHP = 100 + (hero.getLevel() * 100) / 20;
        int totalDamage = currentMonster.getHP() / 20;
        if (hero.getHP() < lowHP) {
            setDamage(totalDamage);
        }
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

