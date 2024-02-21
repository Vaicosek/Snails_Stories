package abilities;

import hero.HeroTemplate;
import monster.MonsterBase;



public class Taunt implements TauntAbilityTemplate {

    private static final int TAUNT_ROUNDS = 2;

    private String name = "Taunt";
    private int manaCost;
    private int totalDamage;
    private boolean unlocked;
    private int remainingTurns = 3;

    public Taunt() {
    }
    @Override
    public void applyTaunt(HeroTemplate hero, MonsterBase monster) {
        monster.setTaunted(true, TAUNT_ROUNDS);
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
    public int getTauntDuration() {
        return TAUNT_ROUNDS;
    }

    @Override
    public void setTauntDuration(int duration) {

    }

    public boolean isTauntAbility() {
        return true; // Indicate that this ability is a taunt ability
    }
}
