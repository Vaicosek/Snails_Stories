package Game.abilities;

import Game.hero.Hero;
import Game.itemshandling.Weapon;

public class SharpWeapons implements BonusAbilityTemplate {

    private String name = "SharpWeapons";
    private int totalDamage;
    private int manaCost;
    private boolean unlocked = false;

    public SharpWeapons() {
    }


    @Override
    public void applyBonus(Hero hero) {
        Weapon equippedWeapon = hero.getEquippedWeapon();
        if (equippedWeapon != null) {
            int currentDamage = equippedWeapon.getDamage();
            double damageIncrease = currentDamage * 0.15; // Increase damage by 15%
            int newDamage = (int) Math.round(currentDamage + damageIncrease);
            equippedWeapon.increaseDamage(newDamage);
            System.out.println(hero.getName() + "'s equipped weapon (" + equippedWeapon.getName() +
                    ") damage increased by 15%. New damage: " + newDamage);

        }
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

