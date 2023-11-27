package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;

public class Purge extends HeroAbility {
    private int remainingTurns;
    private int turnCounter;
    public Purge() {
        setName("Purge");
        setManaCost(40);
        remainingTurns = 0;
        turnCounter = 0;
    }
    public void use(HeroTemplate hero, MonsterBase monster, int currentTurn) {
        int currentMana = hero.getMana();
        int manaCost = getManaCost(); // Get the mana cost from the superclass
        int threshold = (int) ((hero.getLevel()/2) * 0.05 * monster.getHP());


        if (currentMana >= manaCost && monster.getHP() <= threshold) {
            hero.setMana(currentMana - manaCost);
            System.out.println("Used " + getName() + "!");
            setDamage(threshold);
            turnCounter = currentTurn + remainingTurns; // Set the next turn when the ability can be used
        } else {
            System.out.println("Not enough mana to use " + getName() + " or it's not your turn.");
        }
    }
}
