package Game.abilities;

import Game.hero.HeroTemplate;

public class ShadowClone implements EntityAbilityTemplate {

    private String entityName;
    private int randomAttack;

    private String name = "ShadowClone";
    private int totalDamage;
    private int manaCost;
    private boolean unlocked = false;
    public ShadowClone() {

    }

    public boolean isEntitySpell() {
        return true;
    }

    @Override
    public int performAutoAttack() {
        return randomAttack;
    }

    @Override
    public void setRandomAttack(int entityAttack) {

    }

    @Override
    public void summonEntity(HeroTemplate hero) {

    }

    @Override
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
        return unlocked;
    }

    @Override
    public void setUnlocked() {
        unlocked = true;
    }
}


