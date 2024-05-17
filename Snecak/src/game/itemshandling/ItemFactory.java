package game.itemshandling;

import game.hero.HeroTemplate;
import game.monster.Dice;

public class ItemFactory {

    public static ItemBase createRandomItem(HeroTemplate hero) {
        ItemType itemType = getRandomItemType(); // This selects a random ItemType
        return itemType.createItem(hero); // Delegate the creation logic entirely to the ItemType
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

    public static void dropItem(HeroTemplate hero, Inventory inventory) {
        ItemBase randomItem = createRandomItem(hero);
        inventory.addItem(randomItem);
    }
}
