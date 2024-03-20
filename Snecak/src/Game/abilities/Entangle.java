package Game.abilities;

import Game.hero.HeroTemplate;
import Game.monster.MonsterBase;
public class Entangle implements EntangleAbilityTemplate {
    private static final int ENTANGLE_ROUNDS = 1;

    private String name = "Entangle";
    private int totalDamage;
    private int manaCost = 30;
    private boolean unlocked = false;
    public Entangle() {

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
    public void setUnlocked() {
        unlocked = true;
    }

    @Override
    public void applyEntangle(HeroTemplate hero, MonsterBase monster) {
        monster.setEntangled(true, ENTANGLE_ROUNDS);
    }

    @Override
    public int getEntangleDuration() {
        return ENTANGLE_ROUNDS;
    }

    @Override
    public void setEntangleDuration(int duration) {

    }
}
