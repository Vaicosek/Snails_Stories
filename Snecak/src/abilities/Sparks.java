package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;

public class Sparks extends HeroAbility {
    private int remainingTurns;
    private int turnCounter;

    public Sparks() {
        setName("Sparks");
        setManaCost(40); // Set the mana cost for the Sparks ability
        remainingTurns = 0; // Set the initial remaining turns
        turnCounter = 0; // Initialize the turn counter
    }

    public void use(HeroTemplate hero, MonsterBase monster, int currentTurn) {
        int currentMana = hero.getMana();
        int manaCost = getManaCost(); // Get the mana cost from the superclass

        if (currentMana >= manaCost && currentTurn >= turnCounter) {
            hero.setMana(currentMana - manaCost);
            System.out.println("Used " + getName() + "!");
            int damage = Dice.getNextNumber(2, hero.getLevel() * 12);
            turnCounter = currentTurn + remainingTurns; // Set the next turn when the ability can be used
        } else {
            System.out.println("Not enough mana to use " + getName() + " or it's not your turn.");
        }
    }
}