package game.abilities;

import game.hero.HeroTemplate;
import game.monster.Dice;
import game.monster.MonsterBase;
import game.players.Player;

import java.util.List;

public class Fire implements TickAbilityTemplate {
    private String name = "Fire";
    private int totalDamage = 30; // This might need adjustment based on your damage over time logic
    private int manaCost = 20; // Assuming a mana cost
    private boolean unlocked = false;
    private int remainingTurns = 2;

    @Override
    public void cast(HeroTemplate hero, MonsterBase monster, List<MonsterBase> monsters) {
        if (hero.getMana() >= manaCost && !isEffectActive()) {
            hero.setMana(hero.getMana() - manaCost);
            System.out.println("Used " + getName() + "!");
            remainingTurns = 2; // Reset the duration each time the ability is cast
        } else {
            System.out.println("Not enough mana to use " + getName() + ".");
        }
    }

    @Override
    public void onTick(Player player, MonsterBase monster, List<MonsterBase> monsters, int turnCounter) {
        int damage = Dice.getNextNumber(3, totalDamage); // Apply damage calculation logic
        monster.takeDamage(damage);
        System.out.printf("%s deals %d damage to %s. Remaining turns: %d%n", name, damage, monster.getName(), remainingTurns);

        remainingTurns--;
        if (remainingTurns <= 0) {
            System.out.println(getName() + " effect has ended.");
            // Reset or deactivate the ability effect here if necessary
        }
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