package abilities;

import hero.HeroTemplate;

public class AnimalCompanion implements EntityAbilityTemplate {

    private String entityName;
    private int randomAttack;

    private String name;

    public AnimalCompanion() {
        setName("Animal Companion"); // Assuming setName is defined in AbilityTemplate
    }

    public AnimalCompanion setRandomAttack(int randomAttack) {
        this.randomAttack = randomAttack;
        return this;
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
        return false;
    }

    @Override
    public void setUnlocked(boolean unlocked) {

    }

    @Override
    public int performAutoAttack() {
        return 0;
    }

    @Override
    public void summonEntity(HeroTemplate hero) {

    }

    @Override
    public String getEntityName() {
        return null;
    }

    @Override
    public void setEntityName(String name) {

    }

}


