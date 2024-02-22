package abilities;

import hero.HeroTemplate;
import itemshandling.Inventory;
import itemshandling.ItemBase;
import monster.MonsterBase;
import players.Player;

import java.util.Scanner;

public class Throw implements ThrowAbilityTemplate {

    private String name = "Throw";
    private int totalDamage;
    private int manaCost;
    private boolean unlocked;

    public Throw() {
    }
    @Override
    public void cast(Player player, HeroTemplate hero, MonsterBase monster) {
        System.out.println("Used " + getName() + "!");
        throwItem(player.getInventory(), monster);
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
