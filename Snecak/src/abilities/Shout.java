package abilities;

import hero.HeroTemplate;
import monster.MonsterBase;

public class Shout implements TauntAbilityTemplate {

    private static final int TAUNT_ROUNDS = 1;
    private String name = "Shout";
    private int totalDamage;
    private int manaCost;
    private boolean unlocked = false;

    public Shout() {
    }

    @Override
    public void applyTaunt(HeroTemplate hero, MonsterBase monster) {
        monster.setTaunted(true, TAUNT_ROUNDS);
    }

    public boolean isTauntAbility() {
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

    @Override
    public int getTauntDuration() {
        return TAUNT_ROUNDS;
    }

    @Override
    public void setTauntDuration(int duration) {

    }
}