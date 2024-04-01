package game.abilities;

import game.hero.HeroTemplate;
import game.monster.MonsterBase;

public class FuriousStrike implements NormalAbilityTemplate {

    private String name = "FuriousStrike";
    private int totalDamage;
    private int manaCost;
    private boolean unlocked =false;

    public FuriousStrike() {

    }

    @Override
    public void cast(HeroTemplate hero, MonsterBase monster) {
        int damage =(hero.getAttack()/100) * 10 + hero.getAttack();
        setDamage(damage);
        hero.setHp(hero.getHp() -  (hero.getLevel()*100/10));
        System.out.println("Used " + getName() + "!");
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

