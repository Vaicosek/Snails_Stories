package game.abilities;

import game.hero.HeroTemplate;
import game.monster.MonsterBase;

public class MonsterKiller implements AbilityTemplate {

    private String name = "MonsterKiller";
    private int totalDamage;
    private int manaCost;
    private boolean unlocked = false;

    public MonsterKiller() {

    }

    public void apply(HeroTemplate hero, MonsterBase monster) {

        int monsterTier = monster.getTier();

        if (monsterTier == 3 || monsterTier == 4) {
            setDamage(20);
            monster.takeDamage(totalDamage);
            System.out.println("Monster took extra " + totalDamage);
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
  unlocked=true;
    }
}
