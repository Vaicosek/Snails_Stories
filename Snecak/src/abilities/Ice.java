package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;
import players.Player;

import java.util.List;

public class Ice implements TickAbilityTemplate  {
    private int remainingTurns = 2;


    private String name = "Ice";
    private int totalDamage;
    private int manaCost = 20;
    private boolean unlocked;

    public Ice() {
    }

    @Override
    public void cast(HeroTemplate hero, MonsterBase monster, List<MonsterBase> monsters) {
        int currentMana = hero.getMana();
        int manaCost = getManaCost(); // Get the mana cost from the superclass

        if (currentMana >= manaCost) {
            hero.setMana(currentMana - manaCost);
            System.out.println("Used " + getName() + "!");
            int damage = Dice.getNextNumber(1, hero.getLevel() * 7);
            setDamage(damage);

        } else {
            System.out.println("Not enough mana to use " + getName() + " or it's not your turn.");
        }
    }

    @Override
    public void onTick(Player player, MonsterBase monster, List<MonsterBase> monsters, int turnCounter) {
            if (remainingTurns > 0) {

                int damageReduction = Dice.getNextNumber(75,100)/100;
                monster.reduceAttack(damageReduction);

                remainingTurns--;

                if (remainingTurns == 0) {
                    System.out.println(getName() + " effect has ended.");
                }
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
        return false;
    }

    @Override
    public void setUnlocked(boolean unlocked) {

    }
}

