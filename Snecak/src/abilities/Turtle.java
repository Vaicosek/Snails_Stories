package abilities;

import hero.HeroTemplate;
import itemshandling.Armor;

import monster.MonsterBase;

public class Turtle extends HeroAbility {

    private boolean active = false;

    public Turtle() {
        setName("Turtle");
    }

    public void use(HeroTemplate hero) {
        if (!active) {
            System.out.println("You have activated the turtle. To deactivate the turtle, cast this spell in combat again.");

            // Equip the TurtleShell when the Turtle ability is activated
            Armor turtleShell = new Armor(0, 0, 10 + hero.getLevel()); // Adjust the parameters as needed
            hero.equipArmor(turtleShell);

            active = true;
        } else {
            System.out.println("You have deactivated the turtle.");

            // Unequip the TurtleShell when the Turtle ability is deactivated
            hero.unequipArmor(); // Assume you have a method to unequip by armor type

            active = false;
        }
    }
}
