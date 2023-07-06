package abilities;

import hero.HeroTemplate;

public class HeroAbility {
    private String name;
    private int damage;
    private int cost;
    private boolean unlocked;

    public HeroAbility(String name, int damage, int cost) {
        this.name = name;
        this.damage = damage;
        this.cost = cost;
        this.unlocked = false;
    }

    public void use(HeroTemplate hero) {

    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getCost() {
        return cost;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }
}
