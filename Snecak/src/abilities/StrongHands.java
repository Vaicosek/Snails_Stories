package abilities;

import hero.Hero;
import hero.HeroTemplate;
import itemshandling.Inventory;
import itemshandling.Weapon;

import java.util.Scanner;

public class StrongHands extends HeroAbility {
    private Inventory inventory;

    public StrongHands() {
        setName("StrongHands");
        setDamage(0); // Set the initial damage value as needed
        setManaCost(0); // Set the mana cost as needed
        this.inventory = new Inventory(); // Initialize the inventory
    }

    public void use(HeroTemplate hero) {

        inventory.openInventoryMenu(hero);
    }
}
