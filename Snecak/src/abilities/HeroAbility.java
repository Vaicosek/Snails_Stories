package abilities;

import hero.Hero;
import hero.HeroTemplate;

public class HeroAbility {

    private String name;
    private int damage;
    private int manaCost;
    private boolean unlocked;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    public void use(HeroTemplate hero) {
    }


    public void applyPassiveEffect(Hero hero) {
    }

    public void applyActiveEffect(Hero hero) {
    }
}
