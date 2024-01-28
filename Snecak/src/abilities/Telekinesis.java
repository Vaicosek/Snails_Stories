package abilities;

import hero.HeroTemplate;
import itemshandling.Inventory;
import itemshandling.ItemBase;
import monster.MonsterBase;

import java.util.Scanner;

public class Telekinesis extends AbilityBase {

    public Telekinesis() {
        setName("Telekinesis");
        setManaCost(25); // Set the mana cost for the Telekinesis ability
    }

    public void use(HeroTemplate hero, MonsterBase monster, int currentTurn, Inventory inventory) {
        int currentMana = hero.getMana();
        int manaCost = getManaCost(); // Get the mana cost from the superclass

        if (currentMana >= manaCost) {
            hero.setMana(currentMana - manaCost);
            System.out.println("Used " + getName() + "!");
            throwItem(inventory, monster); // Throw an item from the inventory at the monster

        } else {
            System.out.println("Not enough mana to use " + getName() + " or it's not your turn.");
        }
    }

    private void throwItem(Inventory inventory, MonsterBase monster) {
        Scanner scanner = new Scanner(System.in);
        inventory.printInventory(); // Display the items in the inventory

        System.out.print("Enter the name of the item you want to throw: ");
        String itemName = scanner.nextLine();

        if (inventory.hasItemByName(itemName)) {
            ItemBase thrownItem = inventory.getItemByName(itemName); // Retrieve the item to throw

        monster.takeDamage(thrownItem.getDamage());

            System.out.println("You threw " + itemName + " at the monster!");
        } else {
            System.out.println("Item not found in inventory or invalid item name.");
        }
    }
}
