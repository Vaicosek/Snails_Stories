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



    private void displayMenuOptions() {
        logger.info("\nInventory Menu:");
        logger.info("1. Equip Armor");
        logger.info("2. Equip Weapon");
        logger.info("3. Use Consumable");
        logger.info("4. Browse Inventory");
        logger.info("5. Quit Inventory");
        if (hero.isStrongHandsUnlocked()) {
            logger.info("6. Equip Second Weapon");
        }
        if (hero.isEnchantmentUnlocked()) {
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
}
