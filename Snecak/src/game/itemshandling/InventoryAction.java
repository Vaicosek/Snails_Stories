package game.itemshandling;

import game.hero.HeroTemplate;


import java.util.function.BiConsumer;
import java.util.function.Predicate;

public enum InventoryAction {
    EQUIP_ARMOR("Equip Armor", HeroTemplate::alwaysTrue, Inventory::selectAndEquipArmor),
    EQUIP_WEAPON("Equip Weapon", HeroTemplate::alwaysTrue, Inventory::selectAndEquipWeapon),
    USE_CONSUMABLE("Use Consumable", HeroTemplate::alwaysTrue, Inventory::selectAndUseConsumable),
    PRINT_INVENTORY("Browse Inventory", hero -> true, Inventory::printInventory),
    QUIT_INVENTORY("Quit Inventory", hero -> true, (inventory, hero) -> inventory.quit());

    private final String description;
    private final Predicate<HeroTemplate> condition;
    private final BiConsumer<Inventory, HeroTemplate> action;

    InventoryAction(String description, Predicate<HeroTemplate> condition, BiConsumer<Inventory, HeroTemplate> action) {
        this.description = description;
        this.condition = condition;
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAvailable(HeroTemplate hero) {
        return condition.test(hero);
    }

    public void execute(Inventory inventory, HeroTemplate hero) {
        action.accept(inventory, hero);
    }

    // Helper method to always return true for simplification
    public static boolean alwaysTrue(HeroTemplate hero) {
        return true;
    }
}