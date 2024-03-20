package Game.abilities;

import Game.hero.HeroTemplate;
import Game.monster.Dice;
import Game.monster.MonsterBase;
import Game.players.Player;

import java.util.List;

public class HammerSlam implements AreaAbilityTemplate {

    private String name = "HammerSlam";
    private int totalDamage;
    private int manaCost;
    private boolean unlocked = false;

    public HammerSlam() {

    }

    public void castAreaEffect(HeroTemplate hero, List<MonsterBase> monsters, Player player) {
        int currentMana = hero.getMana();
        if (currentMana >= this.manaCost) {
            hero.setMana(currentMana - this.manaCost);
            System.out.println("Used " + getName() + "!");

            for (MonsterBase monster : monsters) {
                // Calculate damage based on hero's level and apply it to each monster
                int damage = Dice.getNextNumber(2, 2 + (hero.getLevel() * 3));
                setDamage(damage);
                monster.takeDamage(totalDamage);
                System.out.printf("%s deals %d damage to %s.%n", getName(), damage, monster.getName());
            }
        } else {
            System.out.println("Not enough mana to use " + getName() + ".");
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
