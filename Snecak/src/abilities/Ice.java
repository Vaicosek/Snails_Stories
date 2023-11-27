package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;

public class Ice extends HeroAbility {
    private int remainingTurns;
    private int turnCounter;

    public Ice() {
        setName("Ice");
        setManaCost(20);
        remainingTurns = 2;
        turnCounter = 0;

    }

    public void use(HeroTemplate hero, MonsterBase monster, int currentTurn) {
        int currentMana = hero.getMana();
        int manaCost = getManaCost(); // Get the mana cost from the superclass

        if (currentMana >= manaCost && currentTurn >= turnCounter) {
            hero.setMana(currentMana - manaCost);
            System.out.println("Used " + getName() + "!");
            int damage = Dice.getNextNumber(1, hero.getLevel() * 7);
            setDamage(damage);
            applyActiveEffect(monster); // Apply the active effect to the selected monster
            turnCounter = currentTurn + remainingTurns; // Set the next turn when the ability can be used
        } else {
            System.out.println("Not enough mana to use " + getName() + " or it's not your turn.");
        }
    }

    public void applyActiveEffect(MonsterBase monster) {
        if (remainingTurns > 0) {

            int damageReduction = Dice.getNextNumber(75,100)/100;
            monster.reduceAttack(damageReduction);

            remainingTurns--;

            if (remainingTurns == 0) {
                System.out.println(getName() + " effect has ended.");
            }
        } else {
            System.out.println(getName() + " effect has ended.");
        }
    }
}
