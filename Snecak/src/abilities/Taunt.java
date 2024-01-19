package abilities;

import hero.HeroTemplate;
import monster.MonsterBase;

import java.util.List;

public class Taunt extends HeroAbility {

    private static final int TAUNT_ROUNDS = 2;  // Fixed number of rounds for the taunt effect

    public Taunt() {
        setName("Taunt");
        setManaCost(20);  // Set an appropriate mana cost for taunt
    }


    public void use(HeroTemplate hero, MonsterBase monster) {
        // Call the method to apply the taunt effect on the specific target monster
        applyTauntEffect(monster);

        // You can do additional things if needed after applying the taunt effect.
        // For example, print a message indicating the taunting action.
        System.out.println(hero.getName() + " used Taunt!");
    }

    private void applyTauntEffect(MonsterBase targetMonster) {
        // Apply taunt effect to the target monster
        targetMonster.setTaunted(true, TAUNT_ROUNDS);

        // Print a message indicating that the hero is now the target of the specified monster
        System.out.println(targetMonster.getName() + " is now targeting the hero!");
    }

    public boolean isTauntAbility() {
        return true; // Indicate that this ability is a taunt ability
    }
}
