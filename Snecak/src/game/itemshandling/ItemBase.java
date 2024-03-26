package game.itemshandling;

import game.monster.Dice;
import game.players.Player;

import java.util.ArrayList;

public abstract class ItemBase {

    public ItemType itemType;
    public int Damage =0;
    public int Health = 0;
    public int Protection = 0;
    public String Name;

    public ItemBase(ItemType itemType, int damage, int health, int protection) {
        this.itemType = itemType;
        Damage = damage;
        Health = health;
        Protection = protection;
        InitializeName();
        Name = getRandomItemName();
    }

    public String getName() {
        return Name;
    }

    protected ArrayList<String> names;

    String getRandomItemName() {
        int i;
        try {
            i = Dice.getNextNumber(1, names.size() - 1);
            return names.get(i);
        } catch (Exception e) {
            return "";
        }
    }

    public ItemType getItemType() {
        return itemType;
    }

    public static ItemType getRandomItemType() {
        int randomNumber = Dice.getNextNumber(1, 100);

        if (randomNumber <= 60) {
            return ItemType.CONSUMABLE;
        } else if (randomNumber <= 80) {
            return ItemType.ARMOR;
        } else {
            return ItemType.WEAPON;
        }
    }

    public static void DropItem(Player player, Inventory inventory) {
        ItemBase randomItem = createRandomItem(player);
        if (randomItem != null) {
            inventory.addItem(randomItem);
        }
    }



    public static ItemBase createRandomItem(Player player) {
        return ItemFactory.createItem(player);
    }

    protected abstract void InitializeName();

    public int getDamage() {
        return Damage;
    }

    public int getHealth() {
        return Health;
    }

    public int getProtection() {
        return Protection;
    }

    public void setDamage(int damage) {
        Damage = damage;
    }

    public void setHealth(int health) {
        Health = health;
    }

    public void setProtection(int protection) {
        Protection = protection;
    }
}
