package game.abilities;

import game.hero.HeroTemplate;
import game.monster.Dice;
import game.monster.MonsterBase;
import game.players.Player;

import java.util.List;

public class Thorns implements TickAbilityTemplate {
    private int remainingTurns = 5;

    private String name = "Thorns";
    private int manaCost = 50;
    private int totalDamage;
    private boolean unlocked =false;


    public Thorns() {
    }

    @Override
    public void cast(HeroTemplate hero, MonsterBase monster, List<MonsterBase> monsters) {
        int currentMana = hero.getMana();
        int manaCost = getManaCost(); // Get the mana cost from the superclass
        if (currentMana >= manaCost) {
            for (MonsterBase currentMonster : monsters) {
                int damage = Dice.getNextNumber(0,4 + hero.getLevel());
                setDamage(damage);
                monster.takeDamage(totalDamage);
                hero.setMana(currentMana - manaCost);
                System.out.println("Used " + getName() + "!");


            }
        }
        else {
            System.out.println("Not enough mana to use " + getName() + " or it's not your turn.");
        }
    }

    @Override
    public void onTick(Player player, MonsterBase monster, List<MonsterBase> monsters, int turnCounter) {
        if (remainingTurns > 0) {

            for (MonsterBase currentMonster : monsters) {
                int damagePerTurn = (monster.getHp() / 100) * 3;

                monster.takeDamage(damagePerTurn);

                remainingTurns--;

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
        return unlocked;
    }

    @Override
    public void setUnlocked() {

    }

    @Override
    public int getRemainingTurns() {
        return remainingTurns;
    }

    @Override
    public void setRemainingTurns(int turns) {
        this.remainingTurns = turns;
    }

    @Override
    public boolean isEffectActive() {
        return remainingTurns > 0;
    }
}

