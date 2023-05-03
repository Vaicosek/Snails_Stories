/*package itemshandling;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<ItemBase> items;

    public Inventory() {
        this.items = new ArrayList<ItemBase>();
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

    public boolean hasItemByName(String itemName) {
        for (ItemBase item : items) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public void printInventory() {
        System.out.println("Inventory:");
        for (ItemBase item : items) {
            System.out.println("- " + item.getName());
            switch (item) {
                case Weapon weapon -> System.out.println("  Damage: " + item.getDamage());
                case Armor armor -> System.out.println("  Protection: " + item.getProtection());
                case Consumable consumable -> System.out.println("  Health: " + item.getHealth());
                default -> {}
            }
        }
    }
}*/
