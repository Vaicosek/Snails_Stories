package Game.abilities;

import Game.hero.HeroTemplate;
import Game.monster.Dice;
import Game.monster.MonsterBase;

public class Rage implements NormalAbilityTemplate {

    private String name = "Rage";
    private int totalDamage;
    private int manaCost = 40;
    private boolean unlocked;

    public Rage() {

    }
    @Override
    public void cast(HeroTemplate hero, MonsterBase monster) {
        int damage = Dice.getNextNumber(hero.getAttack() + hero.getAttack(),hero.getAttack() + hero.getAttack() + hero.getAttack());
        setDamage(damage);
        System.out.println("Used " + getName() + "!");
        int toll = hero.getHP() - damage/5;
        hero.setHP(toll);
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


