package itemshandling;

import hero.HeroTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory {
    private List<ItemBase> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public List<ItemBase> getItems() {
        return items;
    }

    public void addItem(ItemBase item) {
        items.add(item);
    }

    public void removeItem(ItemBase item) {
        items.remove(item);
    }

    public boolean hasItem(ItemBase item) {
        return items.contains(item);
    }

    public void openInventoryMenu(HeroTemplate hero) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Inventory Menu:");
            System.out.println("1. Equip Armor");
            System.out.println("2. Equip Weapon");
            System.out.println("3. Use Consumable");
            System.out.println("4. Browse Inventory");
            System.out.println("5. Quit Inventory");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1 -> equipArmor(hero);
                case 2 -> equipWeapon(hero);

                case 4 -> printInventory();
                case 5 -> {
                    return; // Exit the inventory menu
                }
                default -> System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
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
        System.out.println("Inventory:");
        for (ItemBase item : items) {
            System.out.println("- " + item.getName());
            switch (item) {
                case Weapon weapon -> System.out.println("  Damage: " + item.getDamage());
                case Armor armor -> System.out.println("  Protection: " + item.getProtection());
                case Consumable consumable -> System.out.println("  Health: " + item.getHealth());
                default -> {
                }
            }
        }
    }
    public void useConsumable(HeroTemplate hero) {
        List<Consumable> availableConsumables = new ArrayList<>();
        System.out.println("Available Consumables:");
        int index = 1;
        for (ItemBase item : items) {
            if (item.getItemType() == ItemType.CONSUMABLE) {
                Consumable consumable = (Consumable) item;
                availableConsumables.add(consumable);
                System.out.println(index + ". " + consumable.getName() + " (Health: " + consumable.getHealth() + ")");
                index++;
            }
        }

        if (availableConsumables.isEmpty()) {
            System.out.println("No consumables available in the inventory.");
            return;
        }

        System.out.print("Enter the number of the consumable you want to use (or 'q' to cancel): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("q")) {
            return;
        }

        try {
            int consumableIndex = Integer.parseInt(input) - 1;
            if (consumableIndex >= 0 && consumableIndex < availableConsumables.size()) {
                Consumable consumableToUse = availableConsumables.get(consumableIndex);
                int healthRestored = consumableToUse.getHealth();
                hero.heal(healthRestored); // Assuming there's a heal method in your HeroTemplate class
                items.remove(consumableToUse); // Remove the used consumable from inventory
                System.out.println(hero.getName() + " used " + consumableToUse.getName() + " and restored " + healthRestored + " health.");
            } else {
                System.out.println("Invalid choice. Please enter a valid number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number or 'q' to cancel.");
        }
    }

    public void equipArmor(HeroTemplate hero) {
        List<Armor> availableArmors = new ArrayList<>();
        System.out.println("Available Armors:");
        int index = 1;
        for (ItemBase item : items) {
            if (item.getItemType() == ItemType.ARMOR) {
                Armor armor = (Armor) item;
                availableArmors.add(armor);
                System.out.println(index + ". " + armor.getName() + " (Protection: " + armor.getProtection() + ")");
                index++;
            }
        }

        if (availableArmors.isEmpty()) {
            System.out.println("No armor available in the inventory.");
            return;
        }

        System.out.print("Enter the number of the armor you want to equip (or 'q' to cancel): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("q")) {
            return;
        }

        try {
            int armorIndex = Integer.parseInt(input) - 1;
            if (armorIndex >= 0 && armorIndex < availableArmors.size()) {
                Armor armorToEquip = availableArmors.get(armorIndex);
                hero.equipArmor(armorToEquip);
            } else {
                System.out.println("Invalid choice. Please enter a valid number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number or 'q' to cancel.");
        }
    }

    public void equipWeapon(HeroTemplate hero) {
        List<Weapon> availableWeapons = new ArrayList<>();
        System.out.println("Available Weapons:");
        int index = 1;
        for (ItemBase item : items) {
            if (item.getItemType() == ItemType.WEAPON) {
                Weapon weapon = (Weapon) item;
                availableWeapons.add(weapon);
                System.out.println(index + ". " + weapon.getName() + " (Damage: " + weapon.getDamage() + ")");
                index++;
            }
        }

        if (availableWeapons.isEmpty()) {
            System.out.println("No weapons available in the inventory.");
            return;
        }

        System.out.print("Enter the number of the weapon you want to equip (or 'q' to cancel): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("q")) {
            return;
        }

        try {
            int weaponIndex = Integer.parseInt(input) - 1;
            if (weaponIndex >= 0 && weaponIndex < availableWeapons.size()) {
                Weapon weaponToEquip = availableWeapons.get(weaponIndex);
                hero.equipWeapon(weaponToEquip);
            } else {
                System.out.println("Invalid choice. Please enter a valid number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number or 'q' to cancel.");
        }
    }
}
