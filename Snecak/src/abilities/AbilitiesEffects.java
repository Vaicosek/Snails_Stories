package abilities;

import hero.HeroTemplate;

public class AbilitiesEffects {
    // Define your passive effects here
    public static void applyInquisition(HeroTemplate hero) {
        double damageMultiplier = 1.10; // 10% increase in damage
        int baseDamage = hero.getAttack();
        int increasedDamage = (int) (baseDamage * damageMultiplier);
        hero.(increasedDamage);
    }

    public static void removeInquisition(HeroTemplate hero) {
        // Implement the logic to remove the Inquisition effect here if needed
    }

    // You can add more passive effects here as needed
}
