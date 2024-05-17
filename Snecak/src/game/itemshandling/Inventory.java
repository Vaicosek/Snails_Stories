package game.itemshandling;

import game.hero.HeroTemplate;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Inventory {
    private List<ItemBase> items = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(Inventory.class.getName());
    private HeroTemplate hero;
    private final Scanner scanner = new Scanner(System.in);

    public Inventory(HeroTemplate hero) {
        this.hero = hero;

    }

    public void addItem(ItemBase item) {
        items.add(item);
        logger.info(item.getName() + " has been added to your inventory.");
    }

    public void selectAndEquipArmor() {
        List<ItemBase> armors = items.stream()
                .filter(item -> item.getItemType() == ItemType.ARMOR)
                .collect(Collectors.toList());
        ItemBase selected = selectItemFromList(armors, "Armor");
        if (selected != null) {
            hero.equipArmor(selected);
        }
    }

    public void selectAndEquipWeapon() {
        List<ItemBase> weapons = items.stream()
                .filter(item -> item.getItemType() == ItemType.WEAPON)
                .collect(Collectors.toList());
        ItemBase selected = selectItemFromList(weapons, "Weapon");
        if (selected != null) {
            hero.equipWeapon(selected);
        }
    }

    public void selectAndUseConsumable() {
        List<ItemBase> consumables = items.stream()
                .filter(item -> item.getItemType() == ItemType.CONSUMABLE)
                .collect(Collectors.toList());
        ItemBase selected = selectItemFromList(consumables, "Consumable");
        if (selected != null) {
            hero.useConsumable(selected);
            logger.info("Used " + selected.getName());
        }
    }

    private ItemBase selectItemFromList(List<ItemBase> items, String itemType) {
        if (items.isEmpty()) {
            logger.info("No " + itemType + "s available.");
            return null;
        }
        logger.info("Available " + itemType + "s:");
        for (int i = 0; i < items.size(); i++) {
            logger.info((i + 1) + ": " + items.get(i).getName() + " - " + items.get(i).getDescription());
        }
        logger.info("Enter the number of the " + itemType + " to equip, or 0 to cancel:");
        String input = scanner.nextLine();
        int choice = Integer.parseInt(input);
        if (choice > 0 && choice <= items.size()) {
            return items.get(choice - 1);
        }
        return null;
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

    public void printInventoryDirectly(Inventory inventory) {
        if (inventory == null || inventory.items.isEmpty()) {
            logger.info("Inventory is empty");
            return;
        }
        logger.info("Listing all items in inventory");
        for (ItemBase item : inventory.items) {
            logger.info(item.getName() + " - " + item.getDescription());
        }
    }


    public void removeItem(ItemBase item) {
        if (item != null && items.contains(item)) {
            items.remove(item);
            logger.info(item.getName() + " has been removed from your inventory.");
        } else {
            logger.warning("Item not found in inventory.");
        }
    }


    public static void quitDirectly(Inventory inventory, HeroTemplate hero) {
        logger.info("Exiting inventory menu.");

    }

    public void printInventoryDirectly(HeroTemplate heroTemplate) {
    }
}

