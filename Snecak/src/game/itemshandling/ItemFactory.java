package game.itemshandling;

import game.monster.Dice;
import game.players.Player;

public class ItemFactory {

    public static ItemBase createRandomItem(Player player) {
        ItemType itemType = getRandomItemType(); // This selects a random ItemType
        return itemType.createItem(player); // Delegate the creation logic entirely to the ItemType
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

    public static void dropItem(Player player, Inventory inventory) {
        ItemBase randomItem = createRandomItem(player);
        inventory.addItem(randomItem);
    }

}

