package Game.abilities;

import Game.hero.HeroTemplate;

public class Conjurer implements EntityAbilityTemplate {
    private String entityName;
    private int randomAttack;

    private String name = "Conjurer";
    private int totalDamage;
    private int manaCost = 50;
    private boolean unlocked = false;

    public Conjurer() {

    }


    public boolean isEntitySpell() {
        return true;
    }

    @Override
    public int performAutoAttack() {
        return randomAttack;
    }

    @Override
    public void setRandomAttack(int randomAttack) {
        this.randomAttack = randomAttack;
    }

    @Override
    public void summonEntity(HeroTemplate hero) {

    }

    public String getEntityName() {
        return entityName;
    }

    @Override
    public void setEntityName(String name) {

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
