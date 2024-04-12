package game.itemshandling;

import game.hero.HeroTemplate;
import game.players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Inventory {
    private List<ItemBase> items = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(Inventory.class.getName());
    private HeroTemplate hero;
    private Player player;

    public Inventory(HeroTemplate hero, Player player) {
        this.hero = hero;
        this.player = player;
    }

    public void selectAndEquipArmor() {
        // Let the user select an armor from the inventory and call hero.equipArmor(armor)
    }

    public void selectAndEquipWeapon() {
        // Let the user select a weapon from the inventory and call hero.equipWeapon(weapon)
    }

    public void selectAndUseConsumable() {
        // Let the user select a consumable from the inventory and call hero.useConsumable(consumable)
    }


    public void displayMenuOptions() {
        int optionNumber = 1;
        for (InventoryAction action : InventoryAction.values()) {
            if (action.isAvailable(hero)) {
                logger.info(optionNumber + ". " + action.getDescription());
                optionNumber++;
            }
        }
        logger.info("Choose an option: ");
    }

    public void openInventoryMenu() {
        Scanner scanner = new Scanner(System.in);
        displayMenuOptions(); // Make sure to display the options before entering the loop
        while (true) {
            String input = scanner.nextLine();
            try {
                int choice = Integer.parseInt(input) - 1;
                if (choice >= 0 && choice < InventoryAction.values().length) {
                    InventoryAction selectedAction = InventoryAction.values()[choice];
                    if (selectedAction.isAvailable(hero)) {
                        // Execute the selected action
                        executeAction(selectedAction);
                        if (selectedAction == InventoryAction.QUIT_INVENTORY) break;
                    } else {
                        logger.warning("This action is currently unavailable.");
                    }
                } else {
                    logger.warning("Invalid choice. Please choose a valid option.");
                }
            } catch (NumberFormatException e) {
                logger.warning("Invalid input. Please enter a number.");
            }
        }
    }

    private void executeAction(InventoryAction action) {
        action.execute(this, hero);
    }


    public boolean hasItemByName(String itemName) {
        for (ItemBase item : items) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public ItemBase getItemByName(String itemName) {
        for (ItemBase item : items) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void printInventory() {
        logger.info("Inventory:");
        for (ItemBase item : items) {
            logger.info("- " + item.getName() + " (" + item.getItemType().getAttributeDisplay(item) + ")");
        }
    }
}
