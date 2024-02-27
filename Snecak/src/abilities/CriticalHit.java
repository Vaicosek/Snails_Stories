package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;

public class CriticalHit implements AbilityTemplate {

    private String name = "CriticalHit";
    private int totalDamage;
    private int manaCost;
    private boolean unlocked = false;


    public CriticalHit() {

    }


    public void apply(HeroTemplate hero, MonsterBase monster) {
        int percent = Dice.getNextNumber(0, 100);
        if (percent >= 33) {
            int damage = (hero.getAttack() / 100) * 20 + hero.getAttack();
            setDamage(damage);
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
    public void setUnlocked() {
        unlocked = true;
    }
}


