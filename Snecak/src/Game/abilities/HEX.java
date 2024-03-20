package Game.abilities;

import Game.hero.HeroTemplate;
import Game.monster.MonsterBase;

public class HEX implements EntangleAbilityTemplate {
    private static final int ENTANGLE_ROUNDS = 2;

    private String name = "HEX";
    private int totalDamage;
    private int manaCost = 50;
    private boolean unlocked =false;

    public HEX( ) {

    }

    @Override
    public void applyEntangle(HeroTemplate hero, MonsterBase monster) {

        monster.setEntangled(true,ENTANGLE_ROUNDS);
    }

    @Override
    public int getEntangleDuration() {
        return ENTANGLE_ROUNDS;
    }

    @Override
    public void setEntangleDuration(int duration) {

    }

    public boolean isEntangleAbility() {
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
