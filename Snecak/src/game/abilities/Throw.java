package game.abilities;

import game.hero.HeroTemplate;
import game.itemshandling.Inventory;
import game.itemshandling.ItemBase;
import game.monster.MonsterBase;
import game.players.Player;

import java.util.Scanner;

public class Throw implements ThrowAbilityTemplate {

    private String name = "Throw";
    private int totalDamage;
    private int manaCost;
    private boolean unlocked =false;

    public Throw() {
    }
    @Override
    public void cast(Player player, HeroTemplate hero, MonsterBase monster) {
        System.out.println("Used " + getName() + "!");
        throwItem(player.getInventory(), monster);
    }

    public void throwItem(Inventory inventory, MonsterBase monster) {
        Scanner scanner = new Scanner(System.in);
        inventory.printInventoryDirectly(); // Display the items in the inventory

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
