package abilities;

import hero.HeroTemplate;

public class Fire extends HeroAbility {
    public Fire() {
        setName("Fire");
        setDamage(50); // Set the damage for the Fire ability
        setManaCost(30); // Set the mana cost for the Fire ability
    }

    public void use(HeroTemplate hero) {
        int currentMana = hero.getMana();
        int manaCost = getManaCost(); // Get the mana cost from the superclass
        if (currentMana >= manaCost) {
            int damageDealt = getDamage(); // Get the damage from the superclass
            hero.setMana(currentMana - manaCost);
            System.out.println("Used " + getName() + "! Dealt " + damageDealt + " damage.");
        } else {
            System.out.println("Not enough mana to use " + getName() + ".");
        }
    }
}
