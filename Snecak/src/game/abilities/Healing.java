package game.abilities;

import game.hero.HeroTemplate;
import game.monster.MonsterBase;

public class Healing implements NormalAbilityTemplate {


    private String name = "Healing";
    private int totalDamage;
    private int manaCost = 30;
    private boolean unlocked = false;


    public Healing() {


    }

    @Override
    public void cast(HeroTemplate hero, MonsterBase monster) {
        int currentMana = hero.getMana();
        if (currentMana >= manaCost) {
            int maxHealth = hero.getHp();
            double healAmount = maxHealth * 0.1;
            int currentHealth = hero.getHp();
            int newHealth = Math.min(currentHealth + (int) healAmount, maxHealth);
            hero.setHp(newHealth);

            hero.setMana(currentMana - manaCost);
            System.out.println("Healed for " + (int) healAmount + " HP. Current HP: " + newHealth);
        } else {
            System.out.println("Not enough mana to use Healing.");
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
        return false;
    }

    @Override
    public void setUnlocked() {
        unlocked = true;
    }
}

