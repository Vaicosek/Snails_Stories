package abilities;

import hero.Hero;
import hero.HeroTemplate;

public class ExtraHP implements BonusAbilityTemplate {
    private String name = "ExtraHP";
    private int totalDamage;
    private int manaCost;
    private boolean unlocked = false;

    public ExtraHP() {


    }

    @Override
    public void applyBonus(Hero hero) {
        int extraHP = hero.getLevel() * 10;
        int newHP = 100 + extraHP;
        hero.setHP(newHP);
        System.out.println("Gained " + extraHP + " HP. Total HP: " + newHP);
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