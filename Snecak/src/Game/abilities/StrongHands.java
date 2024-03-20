package Game.abilities;

import Game.hero.HeroTemplate;
import Game.monster.MonsterBase;

public class StrongHands implements NormalAbilityTemplate {

    private String name = "StrongHands";
    private int totalDamage;
    private int manaCost = 60;
    private boolean unlocked = false;

    public StrongHands() {

    }
        @Override
    public void cast(HeroTemplate hero, MonsterBase monster) {

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
