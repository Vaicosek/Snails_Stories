package abilities;

import hero.Hero;
import hero.HeroTemplate;
import monster.MonsterBase;

import java.util.List;

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

    public void use(HeroTemplate hero, List<MonsterBase> monsters) {
    }


    public void applyPassiveEffect(Hero hero) {
    }

    public void applyActiveEffect(Hero hero) {
    }

    public boolean isSpellAreaEffect() {
        return false; // By default, assume the spell doesn't target all monsters
    }

    public boolean isSpellTaunt() {
        return false; // By default
    }

    public boolean isEntitySpell() {
        return false;
    }

    public int getTauntRounds() {
        return 0;
    }

    public void useOnSelf(Hero hero) {
    }
}
