package Game.abilities;

import Game.hero.HeroTemplate;

public class Illusion implements EntityAbilityTemplate {

    private String entityName;
    private int randomAttack;
    private boolean unlocked = false;

    private String name;

    public Illusion() {
        setName("Illusion");

    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public void setDamage(int totalDamage) {

    }

    @Override
    public int getManaCost() {
        return 0;
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
}
