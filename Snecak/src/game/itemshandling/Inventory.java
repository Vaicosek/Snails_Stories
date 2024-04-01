package game.itemshandling;

import game.hero.HeroTemplate;
import game.players.Player;
import lombok.Getter;
import java.util.*;
import java.util.function.Supplier;
import java.util.logging.Logger;


@Getter
public class Inventory {
    private final List<ItemBase> items = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(Inventory.class.getName());
    private static final String INVALID_INPUT = "Invalid choice, Please enter a valid number.";
    private static final String INPUT_QUIT = "or 'q' to cancel.";
    private static final String DAMAGE = "Damage";
    private final HeroTemplate hero;
    private final Player player;
    private final Map<Integer, Runnable> menuActions = new HashMap<>();
    public Inventory(HeroTemplate hero, Player player) {
        this.hero = hero;
        this.player = player;
        initializeMenuActions();
    }

    private void initializeMenuActions() {
        menuActions.put(1, this::equipArmor);
        menuActions.put(2, this::equipWeapon);
        menuActions.put(3, this::useConsumable);
        menuActions.put(4, this::printInventory);
        menuActions.put(5, () -> {}); // No operation for quit, handled by breaking loop
        addConditionalAction(6, this::isStrongHandsUnlocked, this::equipSecondWeapon);
        addConditionalAction(7, this::isEnchantmentUnlocked, this::applyEnchantment);
    }

    private void addConditionalAction(int key, Supplier<Boolean> conditionSupplier, Runnable action) {
        menuActions.put(key, () -> {
            if (Boolean.TRUE.equals(conditionSupplier.get())) {
                action.run();
            } else {
                logger.info("This ability is not yet unlocked!");
            }
        });
    }


    public void openInventoryMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                displayMenuOptions();
                int choice = Integer.parseInt(scanner.nextLine());

                Runnable action = menuActions.get(choice);
                if (action != null) {
                    action.run();
                    if (choice == 5) break; // Quit
                } else {
                    logger.warning("Invalid choice. Please choose a valid option.");
                }
            }
        } catch (Exception e) {
            logger.warning("An error occurred: " + e.getMessage());
        }
    }

    private boolean isStrongHandsUnlocked() {

        return hero.getAbilities().stream().anyMatch(ability -> "StrongHands".equals(ability.getName()) && ability.isUnlocked());
    }

    private boolean isEnchantmentUnlocked() {

        return hero.getAbilities().stream().anyMatch(ability -> "Enchantment".equals(ability.getName()) && ability.isUnlocked());
    }

    private void displayMenuOptions() {
        logger.info("\nInventory Menu:");
        logger.info("1. Equip Armor");
        logger.info("2. Equip Weapon");
        logger.info("3. Use Consumable");
        logger.info("4. Browse Inventory");
        logger.info("5. Quit Inventory");
        if (isStrongHandsUnlocked()) {
            logger.info("6. Equip Second Weapon");
        }
        if (isEnchantmentUnlocked()) {
            logger.info("7. Apply Enchantment");
        }
        logger.info("Choose an option: ");
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

    public void useConsumable(HeroTemplate hero) {
        List<ItemBase> availableConsumables = items.stream()
                .filter(item -> item.getItemType() == ItemType.CONSUMABLE)
                .toList();

        if (availableConsumables.isEmpty()) {
            logger.warning("No consumables available in the inventory.");
            return;
        }

        logger.info("Enter the number of the consumable you want to use 'q' to cancel.");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("q")) {
            return;
        }

        try {
            int consumableIndex = Integer.parseInt(input) - 1;
            if (consumableIndex >= 0 && consumableIndex < availableConsumables.size()) {
                ItemBase consumableToUse = availableConsumables.get(consumableIndex);
                int healthRestored = consumableToUse.getHealth();
                hero.heal(healthRestored); // Assuming there's a heal method in your HeroTemplate class
                items.remove(consumableToUse); // Remove the used consumable from inventory
                logger.info(hero.getName() + " used " + consumableToUse.getName() + " and restored " + healthRestored + " health.");
            } else {
                logger.info(INVALID_INPUT);
            }
        } catch (NumberFormatException e) {
            logger.warning(INVALID_INPUT + INPUT_QUIT);
        }
    }

    public void equipArmor(HeroTemplate hero) {
        List<ItemBase> availableArmors = items.stream()
                .filter(item -> item.getItemType() == ItemType.CONSUMABLE)
                .toList();

        if (availableArmors.isEmpty()) {
            logger.info("No armor available in the inventory.");
            return;
        }

        logger.info("Available Armors:");

        for (int i = 0; i< availableArmors.size(); i++) {
                ItemBase armor = availableArmors.get(i);
                logger.info((i + 1) + ". " + armor.getName() + " (Protection: " + armor.getProtection() + ")");
            }


        logger.info("Enter the number of the armor you want to equipped 'q' to cancel.");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("q")) {
            return;
        }

        try {
            int armorIndex = Integer.parseInt(input) - 1;
            if (armorIndex >= 0 && armorIndex < availableArmors.size()) {
                ItemBase armorToEquip = availableArmors.get(armorIndex);
                hero.equipArmor(armorToEquip);
            } else {
                logger.info(INVALID_INPUT);
            }
        } catch (NumberFormatException e) {
            logger.warning(INVALID_INPUT + INPUT_QUIT);
        }
    }

    public void equipWeapon(HeroTemplate hero) {
        List<ItemBase> availableWeapons = items.stream()
                .filter(item -> item.getItemType() == ItemType.CONSUMABLE)
                .toList();

        if (availableWeapons.isEmpty()) {
            logger.warning("No armor available in the inventory.");
            return;
        }

        logger.info("Available Weapons:");
        for (int i = 0; i < availableWeapons.size(); i++) {
            ItemBase weapon = availableWeapons.get(i);
            logger.info((i + 1) + ". " + weapon.getName() + " (Protection: " + weapon.getProtection() + ")");
        }

        logger.info("Enter the number of the weapon you want to equip or 'q' to cancel.");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("q")) {
            return;
        }

        try {
            int weaponIndex = Integer.parseInt(input) - 1;
            if (weaponIndex >= 0 && weaponIndex < availableWeapons.size()) {
                ItemBase weaponToEquip = availableWeapons.get(weaponIndex);
                hero.equipWeapon(weaponToEquip);
            } else {
                logger.warning(INVALID_INPUT);
            }
        } catch (NumberFormatException e) {
            logger.warning(INVALID_INPUT + INPUT_QUIT);
        }
    }
    public void equipSecondWeapon(HeroTemplate hero) {
        List<ItemBase> availableWeapons = items.stream()
                .filter(item -> item.getItemType() == ItemType.CONSUMABLE)
                .toList();

        if (availableWeapons.isEmpty()) {
            logger.info("No weapons available in the inventory for the second slot.");
            return;
        }
        logger.info("Available Weapons for Second Slot:");

        for (int i = 0; i < availableWeapons.size(); i++) {
            ItemBase weapon2 = availableWeapons.get(i);
            logger.info((i + 1) + ". " + weapon2.getName() + " (Protection: " + weapon2.getProtection() + ")");
        }


        logger.info("Enter the number of the weapon you want to equip in the second 'q' to cancel.");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("q")) {
            return;
        }

        try {
            int weaponIndex = Integer.parseInt(input) - 1;
            if (weaponIndex >= 0 && weaponIndex < availableWeapons.size()) {
                ItemBase weapon2 = availableWeapons.get(weaponIndex);
                hero.equipWeapon2(weapon2);
            } else {
                logger.info(INVALID_INPUT);
            }
        } catch (NumberFormatException e) {
            logger.warning(INVALID_INPUT + INPUT_QUIT);
        }
    }

    public void openWeaponSelectionMenu(Player player) {
        List<Weapon> availableWeapons = new ArrayList<>();
        logger.info("Available Weapons:");
        int index = 1;
        for (ItemBase item : items) {
            if (item.getItemType() == ItemType.WEAPON) {
                Weapon weapon = (Weapon) item;
                availableWeapons.add(weapon);
                logger.warning(index + ". " + weapon.getName() + DAMAGE + weapon.getDamage() + ")");
                index++;
            }
        }

        if (availableWeapons.isEmpty()) {
            logger.info("No weapons available in the inventory.");
            return;
        }

        logger.info("Enter the number of the weapon you want to equipped 'q' to cancel.");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("q")) {
            return;
        }

        try {
            int weaponIndex = Integer.parseInt(input) - 1;
            if (weaponIndex >= 0 && weaponIndex < availableWeapons.size()) {
                Weapon weaponToEquip = availableWeapons.get(weaponIndex);
                player.getHero().equipWeapon(weaponToEquip);
            } else {
                logger.info(INVALID_INPUT);
            }
        } catch (NumberFormatException e) {
            logger.warning(INVALID_INPUT + INPUT_QUIT);
        }
    }

    public void openArmorSelectionMenu(Player player) {
        List<Armor> availableArmors = new ArrayList<>();
        logger.info("Available Armors:");
        int index = 1;
        for (ItemBase item : items) {
            if (item.getItemType() == ItemType.ARMOR) {
                Armor armor = (Armor) item;
                availableArmors.add(armor);
                logger.info(index + ". " + armor.getName() + " (Protection: " + armor.getProtection() + ")");
                index++;
            }
        }

        if (availableArmors.isEmpty()) {
            logger.info("No armor available in the inventory.");
            return;
        }

        logger.info("Enter the number of the armor you want to equip 'q' to cancel.");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("q")) {
            return;
        }

        try {
            int armorIndex = Integer.parseInt(input) - 1;
            if (armorIndex >= 0 && armorIndex < availableArmors.size()) {
                Armor armorToEquip = availableArmors.get(armorIndex);
                player.getHero().equipArmor(armorToEquip);
            } else {
                logger.info(INVALID_INPUT);
            }
        } catch (NumberFormatException e) {
            logger.warning(INVALID_INPUT + INPUT_QUIT);
        }
    }

    public void openConsumableSelectionMenu(Player player) {
        List<Consumable> availableConsumables = new ArrayList<>();
        logger.info("Available Consumables:");
        int index = 1;
        for (ItemBase item : items) {
            if (item.getItemType() == ItemType.CONSUMABLE) {
                Consumable consumable = (Consumable) item;
                availableConsumables.add(consumable);
                logger.info(index + ". " + consumable.getName() + " (Health: " + consumable.getHealth() + ")");
                index++;
            }
        }

        if (availableConsumables.isEmpty()) {
            logger.warning("No consumables available in the inventory.");
            return;
        }

        logger.info("Enter the number of the consumable you want to use 'q' to cancel.");
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
                player.getHero().heal(healthRestored); // Assuming there's a heal method in your Player class
                items.remove(consumableToUse); // Remove the used consumable from inventory
                logger.info(player.getHero().getName() + " used " + consumableToUse.getName() + " and restored " + healthRestored + " health.");
            } else {
                logger.info(INVALID_INPUT);
            }
        } catch (NumberFormatException e) {
            logger.warning(INVALID_INPUT + INPUT_QUIT);
        }
    }
}
