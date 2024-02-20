package abilities;

import hero.HeroTemplate;

public class Conjurer implements EntityAbilityTemplate {
    private String entityName;
    private int randomAttack;

    public Conjurer() {
        setName("Conjurer");
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
        return null;
    }

    @Override
    public boolean isUnlocked() {
        return false;
    }

    @Override
    public void setUnlocked(boolean unlocked) {

    }
}
