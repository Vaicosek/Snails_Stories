package Game.abilities;

import Game.hero.HeroTemplate;
import Game.monster.Dice;
import Game.monster.MonsterBase;

public class PrimalRoar implements TauntAbilityTemplate {
    private static final int TAUNT_ROUNDS = 1;
    private String name = "PrimalRoar";
    private int totalDamage;
    private int manaCost = 33;
    private boolean unlocked = false;
    public PrimalRoar() {
    }

    @Override
    public void applyTaunt(HeroTemplate hero, MonsterBase monster) {
        int damage = Dice.getNextNumber(4,4 + hero.getLevel() * 3);
        setDamage(damage);
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
        return false;
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