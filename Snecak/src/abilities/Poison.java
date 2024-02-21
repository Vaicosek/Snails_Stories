package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;
import players.Player;

import java.util.List;

public class Poison implements TickAbilityTemplate {
    private int remainingTurns = 3;
    private String name = "Poison";
    private int totalDamage;
    private int manaCost = 30;
    private boolean unlocked;


    public Poison() {

    }

    @Override
    public void cast(HeroTemplate hero, MonsterBase monster, List<MonsterBase> monsters) {
        int currentMana = hero.getMana();
        int manaCost = getManaCost(); // Get the mana cost from the superclass

        if (currentMana >= manaCost) {
            hero.setMana(currentMana - manaCost);
            System.out.println("Used " + getName() + "!");
            int damage = Dice.getNextNumber(1, ((monster.getHP()/100)* 3));
            setDamage(damage);

        } else {
            System.out.println("Not enough mana to use " + getName() + " or it's not your turn.");
        }
    }

    @Override
    public void onTick(Player player, MonsterBase monster, List<MonsterBase> monsters, int turnCounter) {
        int poisonMath = (((monster.getHP()/100)* 10) /remainingTurns);
        if (remainingTurns > 0) {
            int damageDealt = Dice.getNextNumber(1, poisonMath);


            monster.takeDamage(damageDealt);

            remainingTurns--;

            if (remainingTurns == 0) {
                System.out.println(getName() + " effect has ended.");
            }
        } else {
            System.out.println(getName() + " effect has ended.");
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
    public void setUnlocked(boolean unlocked) {

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
}
