package game.abilities;

import game.hero.HeroTemplate;
import game.monster.MonsterBase;

public class Purge implements NormalAbilityTemplate {
    private String name = "Purge";
    private int totalDamage;
    private int manaCost = 40;
    private boolean unlocked;

    public Purge() {
    }

    @Override
    public void cast(HeroTemplate hero, MonsterBase monster) {
        int currentMana = hero.getMana();
        int manaCost = getManaCost(); // Get the mana cost from the superclass
        int threshold = (int) ((hero.getLevel()/2) * 0.6 * monster.getHp());


        if (currentMana >= manaCost && monster.getHp() <= threshold) {
            hero.setMana(currentMana - manaCost);
            System.out.println("Used " + getName() + "!");
            setDamage(threshold);

        } else {
            System.out.println("Not enough mana to use " + getName() + " or it's not your turn.");
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


