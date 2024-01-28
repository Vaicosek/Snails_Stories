package abilities;

import hero.Hero;
import itemshandling.Weapon;

public class SharpWeapons extends AbilityBase {
    public SharpWeapons() {
        setName("SharpWeapons");
    }


    public void passiveEffect (Hero hero) {
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

}
