package abilities;

import hero.HeroTemplate;
import itemshandling.Inventory;
import monster.MonsterBase;
import players.Player;

import java.util.List;

public class StrongHands implements NormalAbilityTemplate {

    private String name = "StrongHands";
    private int totalDamage;
    private int manaCost = 60;
    private boolean unlocked;

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
        return false;
    }

    @Override
    public void setUnlocked(boolean unlocked) {

    }
}
