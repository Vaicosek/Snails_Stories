package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;

import java.util.List;
import java.util.Scanner;

public class Misdirection implements MisdirectionAbilityTemplate {
    private final int misdirectionDuration = 2;

    private String name = "Misdirection";
    private int totalDamage;
    private int manaCost = 40;
    private boolean unlocked;

    public Misdirection() {
    }


    @Override
    public void misdirect(HeroTemplate hero, MonsterBase monster, List<MonsterBase> monsters)  {
        System.out.println(hero.getName() + " used " + getName() + " on " + monster.getName() + "!");
        monster.setMisdirected(true, misdirectionDuration);
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
        return false;
    }

    @Override
    public void setUnlocked(boolean unlocked) {

    }

    @Override
    public int getMisdirectionDuration() {
        return misdirectionDuration;
    }

    @Override
    public void setMisdirectionDuration(int duration) {

    }
}
