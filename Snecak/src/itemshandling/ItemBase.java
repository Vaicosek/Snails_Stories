package itemshandling;

import monster.Dice;
import players.Player;

import java.util.ArrayList;

public abstract class ItemBase {

    public ItemType itemType;
    public int Damage;
    public int Health;
    public int Protection;
    public String Name;

    public ItemBase(ItemType itemType,int damage, int health, int protection) {
        this.itemType = itemType;
        Damage = damage;
        Health = health;
        Protection = protection;
        Name = getRandomItemName();
        InitializeName();

    }

    public String getName() {
        return Name;
    }

    protected ArrayList<String> ItemNames;

    String getRandomItemName() {
        int i;
        try {
            i = Dice.getNextNumber(1, ItemNames.size() - 1);
            return ItemNames.get(i);
        } catch (Exception e) {
            return "";
        }

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

    public static void  DropItem(Player player) {
        ItemType itemType = getRandomItemType();
        switch (itemType) {
            case CONSUMABLE -> {
                Consumable consumable = new Consumable(0,0,0);
                consumable.setRandomHealth(player);
                player.addItem(consumable);
            }
            case ARMOR -> {
                Armor armor = new Armor(0,0,0);
                armor.setRandomProtection(player);
                player.addItem(armor);
            }
            case WEAPON -> {
                Weapon weapon = new Weapon(0,0,0);
                weapon.setRandomDamage(player);
                player.addItem(weapon);
            }
        }
    }

    protected abstract void InitializeName();
}

