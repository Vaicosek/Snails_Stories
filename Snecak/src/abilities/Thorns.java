package abilities;

import hero.HeroTemplate;
import monster.MonsterBase;

public class Thorns extends HeroAbility {
    private int remainingTurns;
    private int turnCounter;

    public Thorns() {
        setName("Thorns");
        remainingTurns = 5; // Set the initial remaining turns for the effect
        turnCounter = 0; // Initialize the turn counter
    }

    public void use(HeroTemplate hero, MonsterBase monster, int currentTurn) {
        int currentMana = hero.getMana();
        int manaCost = getManaCost(); // Get the mana cost from the superclass

        if (currentMana >= manaCost && currentTurn >= turnCounter) {
            hero.setMana(currentMana - manaCost);
            System.out.println("Used " + getName() + "!");

            applyActiveEffect(monster); // Apply the active effect to the selected monster
            turnCounter = currentTurn + remainingTurns; // Set the next turn when the ability can be used
        } else {
            System.out.println("Not enough mana to use " + getName() + " or it's not your turn.");
        }
    }

    public void applyActiveEffect(MonsterBase monster) {
        if (remainingTurns > 0) {
            int damagePerTurn = (monster.getHP() / 100) * 5; // Calculate bleed damage as a fraction of monster's HP

            monster.takeDamage(damagePerTurn);

            remainingTurns--;

            if (remainingTurns == 0) {
                System.out.println(getName() + " effect has ended.");
            }
        } else {
            System.out.println(getName() + " effect has ended.");
        }
    }
}
