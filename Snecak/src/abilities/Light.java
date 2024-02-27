package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;
import players.Player;

import java.util.List;

public class Light implements TickAbilityTemplate {

    private String name = "Light";
    private int totalDamage;
    private int manaCost = 50;
    private boolean unlocked = false;
    private int remainingTurns = 3;
    public Light() {
    }

    @Override
    public void cast(HeroTemplate hero, MonsterBase monster, List<MonsterBase> monsters) {
        int currentMana = hero.getMana();
        int manaCost = getManaCost();

        if (currentMana >= manaCost) {
            hero.setMana(currentMana - manaCost);
            System.out.println("Used " + getName() + "!");


        } else {
            System.out.println("Not enough mana to use " + getName() + " or it's not your turn.");
        }
    }

    @Override
    public void onTick(Player player, MonsterBase monster, List<MonsterBase> monsters, int turnCounter) {
        if (remainingTurns > 0) {

            int damageReduction = Dice.getNextNumber(65,100)/100;
            monster.reduceAttack(damageReduction);

            remainingTurns--;

        } else {
            System.out.println(getName() + " effect has ended.");
        }
    }

    @Override
    public int getRemainingTurns() {
        return remainingTurns;
    }

    @Override
    public void setRemainingTurns(int turns) {

    }

    @Override
    public boolean isEffectActive() {
        return false;
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
