package game.itemshandling;

import game.hero.HeroTemplate;
import lombok.Getter;


import java.util.function.BiConsumer;
import java.util.function.Predicate;

public enum InventoryAction {
    EQUIP_ARMOR("Equip Armor", hero -> true, (inventory, hero) -> inventory.selectAndEquipArmor()),
    EQUIP_WEAPON("Equip Weapon", hero -> true, (inventory, hero) -> inventory.selectAndEquipWeapon()),
    USE_CONSUMABLE("Use Consumable", hero -> true, (inventory, hero) -> inventory.selectAndUseConsumable()),
    PRINT_INVENTORY("Browse Inventory", hero -> true, Inventory::printInventoryDirectly),
    QUIT_INVENTORY("Quit Inventory", hero -> true, Inventory::quitDirectly);




    @Getter
    private final String description;
    private final Predicate<HeroTemplate> condition;
    private final BiConsumer<Inventory, HeroTemplate> action;

    InventoryAction(String description, Predicate<HeroTemplate> condition, BiConsumer<Inventory, HeroTemplate> action) {
        this.description = description;
        this.condition = condition;
        this.action = action;
    }

    public boolean isAvailable(HeroTemplate hero) {
        return condition.test(hero);
    }

    public void execute(Inventory inventory, HeroTemplate hero) {
        action.accept(inventory, hero);
    }

}