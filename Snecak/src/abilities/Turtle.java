package abilities;

import hero.HeroTemplate;
import itemshandling.Armor;
import monster.MonsterBase;

public class Turtle implements NormalAbilityTemplate {

    private String name = "Turtle";
    private int manaCost;
    private int totalDamage;
    private boolean unlocked =false;
    private int remainingTurns = 3;
    private boolean active = false;

    public Turtle() {
    }
    @Override
    public void cast(HeroTemplate hero, MonsterBase monster) {

        if (!active) {
            System.out.println("You have activated the turtle. To deactivate the turtle, cast this spell in combat again.");

            // Equip the TurtleShell when the Turtle ability is activated
            Armor turtleShell = new Armor(0, 0, 10 + hero.getLevel()); // Adjust the parameters as needed
            hero.equipArmor(turtleShell);

            active = true;
        } else {
            System.out.println("You have deactivated the turtle.");
            hero.unequipArmor();
            active = false;
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


