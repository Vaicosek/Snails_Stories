package abilities;

import hero.HeroTemplate;
import monster.MonsterBase;

public class Inquisition implements AbilityTemplate {

    private String name = "Inquisition";
    private int totalDamage;
    private int manaCost;
    private boolean unlocked;
    public Inquisition() {

    }

    public void apply(HeroTemplate hero, MonsterBase monster) {
        int monsterTier = monster.getTier();

        if (monsterTier == 1 || monsterTier == 2) {
            setDamage(15) ;
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
        return false;
    }

    @Override
    public void setUnlocked(boolean unlocked) {

    }
}

