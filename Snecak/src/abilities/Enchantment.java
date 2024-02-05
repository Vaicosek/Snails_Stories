package abilities;

import hero.HeroTemplate;
import itemshandling.Armor;
import itemshandling.Consumable;
import itemshandling.ItemBase;
import itemshandling.Weapon;
import monster.Dice;
import players.Player;


import java.util.List;
import java.util.Scanner;

public class Enchantment extends AbilityBase {
    public Enchantment() {
        setName("Enchantment");

    }

    public void enchant(HeroTemplate hero, Player player) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What do you want to enchant?");
        System.out.println("1. Weapons");
        System.out.println("2. Armors");
        System.out.println("3. Consumables");
        System.out.print("Enter the number of your choice (or 'q' to cancel): ");

        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("q")) {
            return;
        }

        try {
            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1:
                    enchantWeapons(hero,player);
                    break;
                case 2:
                    enchantArmor(hero,player);
                    break;
                case 3:
                    enchantConsumables(hero,player);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number or 'q' to cancel.");
        }
    }



    public void enchantWeapons(HeroTemplate hero, Player player) {
        List<Weapon> availableWeapons = hero.getAvailableWeapons(hero,player);

        if (availableWeapons.isEmpty()) {
            System.out.println("No weapons available in the inventory.");
            return;
        }

        System.out.println("Available Weapons:");
        printItemList(availableWeapons);

        System.out.print("Enter the number of the weapon you want to enchant (or 'q' to cancel): ");
        handleEnchantInput(hero, availableWeapons);
    }

    public void enchantArmor(HeroTemplate hero, Player player) {
        List<Armor> availableArmors = hero.getAvailableArmors(hero,player);

        if (availableArmors.isEmpty()) {
            System.out.println("No armor available in the inventory.");
            return;
        }

        System.out.println("Available Armors:");
        printItemList(availableArmors);

        System.out.print("Enter the number of the armor you want to enchant (or 'q' to cancel): ");
        handleEnchantInput(hero, availableArmors);
    }

    public void enchantConsumables(HeroTemplate hero, Player player) {
        List<Consumable> availableConsumables = hero.getAvailableConsumables(hero,player);

        if (availableConsumables.isEmpty()) {
            System.out.println("No consumables available in the inventory.");
            return;
        }

        System.out.println("Available Consumables:");
        printItemList(availableConsumables);

        System.out.print("Enter the number of the consumable you want to enchant (or 'q' to cancel): ");
        handleEnchantInput(hero, availableConsumables);
    }

    private void printItemList(List<? extends ItemBase> items) {
        int index = 1;
        for (ItemBase item : items) {
            System.out.println(index + ". " + item.getName());
            index++;
        }
    }

    private void handleEnchantInput(HeroTemplate hero, List<? extends ItemBase> items) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("q")) {
            return;
        }

        try {
            int itemIndex = Integer.parseInt(input) - 1;
            if (itemIndex >= 0 && itemIndex < items.size()) {
                ItemBase itemToEnchant = items.get(itemIndex);

                int currentDamage = itemToEnchant.getDamage();
                int currentProtection = itemToEnchant.getProtection();
                int currentHealth = itemToEnchant.getHealth();

                int enchantDamage = Dice.getNextNumber(currentDamage,currentDamage/20 +currentDamage);
                int enchantProtection = Dice.getNextNumber(currentProtection,currentProtection/20 +currentProtection);
                int enchantHealth = Dice.getNextNumber(currentHealth, currentHealth/20 +currentHealth);

                itemToEnchant.setProtection(enchantProtection);
                itemToEnchant.setHealth(enchantHealth);
                itemToEnchant.setDamage(enchantDamage);



                System.out.println(hero.getName() + " enchanted " + itemToEnchant.getName() + "!");
            } else {
                System.out.println("Invalid choice. Please enter a valid number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number or 'q' to cancel.");
        }
    }


}
