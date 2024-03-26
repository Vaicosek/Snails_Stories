package game.abilities;

import game.hero.HeroTemplate;
import game.monster.Dice;
import game.monster.MonsterBase;
import game.players.Player;

import java.util.List;

public class Storm implements AreaAbilityTemplate {

    private String name = "Storm";
    private int totalDamage;
    private int manaCost = 60;
    private boolean unlocked = false;

    public Storm() {
    }

    @Override
    public void castAreaEffect(HeroTemplate hero, List<MonsterBase> monsters, Player player) {
        int currentMana = hero.getMana();
        int manaCost = getManaCost(); // Get the mana cost from the superclass

        if (currentMana >= manaCost) {
            hero.setMana(currentMana - manaCost);

            for (MonsterBase monster : monsters) {
                int damage = Dice.getNextNumber(0, hero.getLevel() * 9);
                setDamage(damage);
                monster.takeDamage(totalDamage);
            }

        } else {
            System.out.println("Not enough mana to use " + getName() + " or it's not your turn.");
        }
    }

    public boolean isSpellAreaEffect() {
        return true;
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
